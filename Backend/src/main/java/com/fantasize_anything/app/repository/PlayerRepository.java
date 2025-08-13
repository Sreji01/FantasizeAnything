package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
