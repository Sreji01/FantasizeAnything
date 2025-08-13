package com.fantasize_anything.app.repository;

import com.fantasize_anything.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
