package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.ScoringSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringSystemRepository extends JpaRepository<ScoringSystem, Long> {
}
