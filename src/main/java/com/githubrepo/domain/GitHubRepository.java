package com.githubrepo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubRepository {
    @Id
    private Long id;
    private String name;
    private String description;
    private String owner;
    private String language;
    private int stars;
    private int forks;
    private ZonedDateTime lastUpdated;
}