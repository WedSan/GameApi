package com.project.gameapi.Controller;

import com.project.gameapi.model.Game;
import com.project.gameapi.model.ReqGame;
import com.project.gameapi.model.ReqUpdateGame;
import com.project.gameapi.service.GameService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity createGame(@RequestBody @Valid ReqGame reqGame){
        boolean gameExists = gameService.existsByName(reqGame.name());
        if(gameExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Already exists this game");
        }
        Game game = new Game(reqGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(game));
    }

    @GetMapping
    public ResponseEntity<Page<Game>> getGamelist(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getGameList(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getGameById(@PathVariable Long id){
        Optional<Game> optionalGame = gameService.findById(id);
        if(optionalGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This game doesn't exist");
        }
        Game game = optionalGame.get();
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateGame(@PathVariable Long id, @RequestBody ReqUpdateGame reqGame){
        Optional<Game> optionalGame = gameService.findById(id);
        if(optionalGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This game doesn't exist");
        }
        Game game = optionalGame.get();
        game.update(reqGame);
        return ResponseEntity.status(HttpStatus.OK).body(gameService.save(game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable Long id){
        Optional<Game> optionalGame = gameService.findById(id);
        if(optionalGame.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This game doesn't exist");
        }
        gameService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}