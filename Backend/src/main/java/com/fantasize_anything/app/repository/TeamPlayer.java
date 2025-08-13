package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayer extends JpaRepository<Team, Long> {
}
