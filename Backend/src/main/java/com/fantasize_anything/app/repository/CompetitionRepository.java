package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
