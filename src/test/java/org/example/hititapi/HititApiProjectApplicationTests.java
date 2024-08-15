package org.example.hititapi;

import org.example.hititapi.model.Contributor;
import org.example.hititapi.model.Repository;
import org.example.hititapi.service.HititApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class HititApiProjectApplicationTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HititApiService hititApiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchTopRepositories() {
        Repository[] mockRepositories = {
                new Repository(), new Repository(), new Repository()
        };

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(Repository[].class)))
                .thenReturn(new ResponseEntity<>(mockRepositories, HttpStatus.OK));

        List<Repository> repositories = hititApiService.fetchTopRepositories();

        assertNotNull(repositories);
        assertEquals(3, repositories.size());
    }

    @Test
    void testFetchTopContributors() {
        Contributor[] mockContributors = {
                new Contributor(), new Contributor(), new Contributor()
        };

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(Contributor[].class)))
                .thenReturn(new ResponseEntity<>(mockContributors, HttpStatus.OK));

        List<Contributor> contributors = hititApiService.fetchTopContributors("mock-repo");

        assertNotNull(contributors);
        assertEquals(3, contributors.size());
    }
}
