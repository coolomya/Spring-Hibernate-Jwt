package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value = "select u.*, array_agg(r.role_name) as roles from users u inner join users_roles ur on u.username = :username and ur.users_id = u.id inner join roles r on r.id = ur.roles_id group by 1,2,3,4", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);
}
