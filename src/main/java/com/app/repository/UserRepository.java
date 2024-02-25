package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<List<User>> findByNameContainingIgnoreCase(String name);

	Optional<User> findByEmail(String email);
}