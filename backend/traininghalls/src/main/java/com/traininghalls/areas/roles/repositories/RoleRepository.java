package com.traininghalls.areas.roles.repositories;

import com.traininghalls.areas.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
    Role findOneByAuthority(String authority);
}
