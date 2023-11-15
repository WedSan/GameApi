package com.project.gameapi.service;

import com.project.gameapi.model.Game;
import com.project.gameapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game save(Game game){
        return gameRepository.save(game);
    }

    public Page<Game> getGameList(Pageable pageable){
        return gameRepository.findAll(pageable);
    }

    public boolean existsByName(String name){
        return gameRepository.existsByName(name);
    }
    public Optional<Game> findById(Long id){
        return gameRepository.findById(id);
    }

    public void deleteById(Long id){
        gameRepository.deleteById(id);
    }

    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }


}
