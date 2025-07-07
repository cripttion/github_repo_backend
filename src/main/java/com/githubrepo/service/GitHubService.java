package com.githubrepo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.githubrepo.domain.GitHubRepository;
import com.githubrepo.handler.InvalidInputException;
import com.githubrepo.repository.GitHubRepositoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubService {
    private final GitHubRepositoryRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public List<GitHubRepository> searchAndSaveRepos(String query, String language, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.github.com/search/repositories?q=%s+language:%s&sort=%s",
                query, language, sort);

        List<GitHubRepository> results = new ArrayList<>();
        try {
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = mapper.readTree(response);
            for (JsonNode item : root.get("items")) {
                GitHubRepository repo = new GitHubRepository();
                repo.setId(item.get("id").asLong());
                repo.setName(item.get("name").asText());
                repo.setDescription(item.get("description").asText(null));
                repo.setOwner(item.get("owner").get("login").asText());
                repo.setLanguage(item.get("language").asText(null));
                repo.setStars(item.get("stargazers_count").asInt());
                repo.setForks(item.get("forks_count").asInt());
                repo.setLastUpdated(ZonedDateTime.parse(item.get("updated_at").asText()));

                results.add(repository.save(repo)); // Upsert
            }
        } catch(Exception e)
        {
             throw new InvalidInputException("Invalid Request Found");
        }

        return results;
    }

    public List<GitHubRepository> retrieveRepos(String language, Integer minStars, String sort) {
        try {
            List<GitHubRepository> repos = repository.findFiltered(language, minStars);

            return switch (sort.toLowerCase()) {
                case "forks" -> repos.stream()
                                     .sorted(Comparator.comparingInt(GitHubRepository::getForks).reversed())
                                     .toList();
                case "updated" -> repos.stream()
                                       .sorted(Comparator.comparing(GitHubRepository::getLastUpdated).reversed())
                                       .toList();
                default -> repos.stream()
                                .sorted(Comparator.comparingInt(GitHubRepository::getStars).reversed())
                                .toList();
            };
        } catch (NullPointerException e) {
            throw new RuntimeException("A required parameter is missing or null: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid sort parameter provided: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving repositories: " + e.getMessage());
        }
    }
    
}
