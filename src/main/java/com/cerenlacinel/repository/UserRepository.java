package com.cerenlacinel.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cerenlacinel.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
 
	Optional<User> findByUsername(String username);
}
