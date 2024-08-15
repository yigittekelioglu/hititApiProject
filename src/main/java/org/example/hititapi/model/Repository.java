package org.example.hititapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Repository {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @Column(name = "repository_name")
    private String name;

    @JsonProperty("stargazers_count")
    @Column(name = "stargazers_count")
    private int stargazersCount;

    @JsonProperty("watchers_count")
    @Column(name = "watchers_count")
    private int watchersCount;

    private String language;

    @JsonProperty("open_issues_count")
    @Column(name = "open_issues_count")
    private int openIssuesCount;

    @Embedded
    private License license;

    @ElementCollection
    private List<String> openIssues;
}

