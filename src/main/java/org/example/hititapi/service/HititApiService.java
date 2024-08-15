package org.example.hititapi.service;

import org.example.hititapi.model.Contributor;
import org.example.hititapi.model.Repository;
import org.example.hititapi.repository.ContributorRepository;
import org.example.hititapi.repository.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HititApiService {

    private final RestTemplate restTemplate;
    private final RepositoryRepository repositoryRepository;
    private final ContributorRepository contributorRepository;


    @Autowired
    public HititApiService(RestTemplate restTemplate, RepositoryRepository repositoryRepository, ContributorRepository contributorRepository) {
        this.restTemplate = restTemplate;
        this.repositoryRepository = repositoryRepository;
        this.contributorRepository = contributorRepository;
    }

    public List<Repository> fetchTopRepositories() {
        String repo_url = "https://api.github.com/orgs/apache/repos?per_page=100&sort=updated";

        ResponseEntity<Repository[]> response = restTemplate.exchange(repo_url, HttpMethod.GET, null, Repository[].class);

        List<Repository> repositories = Arrays.stream(response.getBody())
                .sorted((r1, r2) -> Integer.compare(r2.getStargazersCount(), r1.getStargazersCount()))
                .limit(5)
                .collect(Collectors.toList());

        repositoryRepository.saveAll(repositories);

        return repositories;
    }

    public List<Contributor> fetchTopContributors(String repoName) {
        String contributor_url = String.format("https://api.github.com/repos/apache/%s/contributors?per_page=10", repoName);

        ResponseEntity<Contributor[]> response = restTemplate.exchange(contributor_url, HttpMethod.GET, null, Contributor[].class);
        List<Contributor> contributors = Arrays.asList(response.getBody());

        Repository repository = repositoryRepository.findByName(repoName);

        contributors.stream()
                .peek(contributor -> contributor.setRepository(repository))
                .forEach(contributorRepository::save);

        return contributors;
    }


    public void printRepositoriesAndContributors() {
        List<Repository> topRepositories = fetchTopRepositories();
        topRepositories.forEach(repo -> {
            System.out.println("Repository: ");
            System.out.println("\tID: " + (repo.getId() != null ? repo.getId() : "N/A"));
            System.out.println("\tName: " + (repo.getName() != null ? repo.getName() : "N/A"));
            System.out.println("\tStars: " + repo.getStargazersCount());
            System.out.println("\tWatchers: " + repo.getWatchersCount());
            System.out.println("\tLanguage: " + (repo.getLanguage() != null ? repo.getLanguage() : "N/A"));
            System.out.println("\tOpen Issues Count: " + repo.getOpenIssuesCount());
            System.out.println("\tLicense: " + (repo.getLicense() != null ? repo.getLicense().getName() : "N/A"));
            System.out.println("\tOpen Issues: " + (repo.getOpenIssues() != null ? String.join(", ", repo.getOpenIssues()) : "None"));

            List<Contributor> contributors = fetchTopContributors(repo.getName());
            contributors.forEach(contributor -> {
                System.out.println("\tContributor: " + (contributor.getLogin() != null ? contributor.getLogin() : "N/A"));
                System.out.println("\t\tLocation: " + (contributor.getLocation() != null ? contributor.getLocation() : "N/A"));
                System.out.println("\t\tCompany: " + (contributor.getCompany() != null ? contributor.getCompany() : "N/A"));
                System.out.println("\t\tCommits: " + contributor.getContributions());
            });
        });
    }



}
