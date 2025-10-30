package com.fantasize_anything.app.controller;

import com.fantasize_anything.app.domain.FantasyGame;
import com.fantasize_anything.app.dto.FantasyGameDTO;
import com.fantasize_anything.app.service.FantasyGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fantasy-game")
public class FantasyGameController {

    private final FantasyGameService fantasyGameService;

    public FantasyGameController(FantasyGameService fantasyGameService) {
        this.fantasyGameService = fantasyGameService;
    }

    @PostMapping
    public ResponseEntity<FantasyGame> crateFantasyGame(@RequestParam FantasyGameDTO request){
        FantasyGame fantasyGame = fantasyGameService.createFantasyGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(fantasyGame);
    }
}
