package com.fantasize_anything.app.service;

import com.fantasize_anything.app.domain.FantasyGame;
import com.fantasize_anything.app.domain.Player;
import com.fantasize_anything.app.domain.ScoringRule;
import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.FantasyGameDTO;
import com.fantasize_anything.app.exception.UserNotFoundException;
import com.fantasize_anything.app.repository.FantasyGameRepository;
import com.fantasize_anything.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FantasyGameService {

    private final FantasyGameRepository activityRepository;
    private final UserRepository userRepository;

    public FantasyGameService(FantasyGameRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FantasyGame createFantasyGame(FantasyGameDTO dto) {
        FantasyGame fantasyGame = new FantasyGame();
        fantasyGame.setTitle(dto.getName());
        fantasyGame.setDescription(dto.getDescription());
        fantasyGame.setBudget(dto.getBudget());
        fantasyGame.setNumberOfPlayersPerTeam(dto.getNumberOfPlayersPerTeam());

        User creator = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        fantasyGame.setCreator(creator);

        dto.getScoringRules().forEach(sr -> {
            ScoringRule scoringRule = new ScoringRule();
            scoringRule.setDescription(sr.getDescription());
            scoringRule.setNumberOfPoints(sr.getNumberOfPoints());
            fantasyGame.addScoringRule(scoringRule);
        });

        dto.getPlayers().forEach(p -> {
            Player player = new Player();
            player.setFirstName(p.getFirstName());
            player.setLastName(p.getLastName());
            player.setBirthDate(p.getBirthDate());
            player.setPrice(p.getPrice());
            player.setImage(p.getImage());
            fantasyGame.addPlayer(player);
        });

        return activityRepository.save(fantasyGame);
    }
}
