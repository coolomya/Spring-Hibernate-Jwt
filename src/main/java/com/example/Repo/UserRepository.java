package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);
}
