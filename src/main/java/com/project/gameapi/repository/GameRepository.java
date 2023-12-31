package com.project.gameapi.repository;

import com.project.gameapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByName(String name);
    Optional<Game> findById(Long id);
}
