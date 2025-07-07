package com.githubrepo.repository;

import com.githubrepo.domain.GitHubRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GitHubRepositoryRepository extends JpaRepository<GitHubRepository, Long> {

    @Query("SELECT g FROM GitHubRepository g WHERE " +
    "(:language IS NULL OR g.language = :language) AND " +
    "(:minStars IS NULL OR g.stars >= :minStars)")
List<GitHubRepository> findFiltered(@Param("language") String language,
                                 @Param("minStars") Integer minStars);

}
