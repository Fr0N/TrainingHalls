package com.traininghalls.areas.users.repositories;

import com.traininghalls.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);
}
