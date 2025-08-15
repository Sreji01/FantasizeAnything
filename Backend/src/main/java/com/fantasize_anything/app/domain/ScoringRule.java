package com.fantasize_anything.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scoring_system")
@Getter
@Setter
@NoArgsConstructor
public class ScoringRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int numberOfPoints;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
