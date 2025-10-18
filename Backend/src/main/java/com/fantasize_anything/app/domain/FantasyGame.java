package com.fantasize_anything.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fantasy_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FantasyGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate dateCreated;
    private BigDecimal budget;
    private int numberOfPlayers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<League> leagues;

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScoringRule> scoringRules;

    @PrePersist
    public void prePersist(){
        dateCreated = LocalDate.now();
    }

    public void addScoringRule(ScoringRule rule){
        if(scoringRules == null){
            scoringRules = new ArrayList<>();
        }
        rule.setFantasyGame(this);
        scoringRules.add(rule);
    }
}
