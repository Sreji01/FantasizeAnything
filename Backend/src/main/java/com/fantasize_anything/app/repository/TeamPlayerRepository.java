package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {
}
