package com.fantasize_anything.app.dto;

import com.fantasize_anything.app.domain.ScoringRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityInput {

    private String name;
    private String description;
    private BigDecimal budget;
    private int numberOfPlayers;
    private Long userId;
    private List<ScoringRule> scoringRules;
}
