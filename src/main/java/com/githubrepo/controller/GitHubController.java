package com.githubrepo.controller;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.githubrepo.domain.GitHubRepository;
import com.githubrepo.dto.SearchRequest;
import com.githubrepo.service.GitHubService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService service;

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> searchRepos(@RequestBody SearchRequest request) {
        List<GitHubRepository> repos = service.searchAndSaveRepos(
                request.getQuery(), request.getLanguage(), request.getSort());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Repositories fetched and saved successfully");
        response.put("repositories", repos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/repositories")
    public ResponseEntity<Map<String, Object>> getRepos(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(defaultValue = "stars") String sort) {

        List<GitHubRepository> repos = service.retrieveRepos(language, minStars, sort);
        Map<String, Object> response = new HashMap<>();
        response.put("repositories", repos);
        return ResponseEntity.ok(response);
    }

   
}

