package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.ScoringRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringRuleRepository extends JpaRepository<ScoringRule, Long> {
}
