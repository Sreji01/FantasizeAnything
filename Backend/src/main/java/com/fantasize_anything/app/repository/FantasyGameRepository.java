package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.FantasyGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FantasyGameRepository extends JpaRepository<FantasyGame, Long> {
}
