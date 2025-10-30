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

    @Column(name = "date_created")
    private LocalDate dateCreated;
    private BigDecimal budget;

    @Column(name = "number_of_players_per_team")
    private int numberOfPlayersPerTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "fantasyGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ScoringRule> scoringRules = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }

    public void addLeague(League league) {
        leagues.add(league);
        league.setFantasyGame(this);
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setFantasyGame(this);
    }

    public void addScoringRule(ScoringRule rule) {
        scoringRules.add(rule);
        rule.setFantasyGame(this);
    }

}
