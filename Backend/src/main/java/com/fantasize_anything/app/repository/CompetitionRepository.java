package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<League, Long> {
}
