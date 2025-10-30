package com.fantasize_anything.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scoring_rule")
@Getter
@Setter
@NoArgsConstructor
public class ScoringRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(name = "number_of_points")
    private int numberOfPoints;

    @ManyToOne
    @JoinColumn(name = "fantasy_game_id")
    private FantasyGame fantasyGame;
}
