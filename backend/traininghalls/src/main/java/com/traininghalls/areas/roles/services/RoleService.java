package com.traininghalls.areas.roles.services;

import com.traininghalls.areas.roles.entities.Role;

public interface RoleService {
    Role getDefaultRole();

    Role findOneByAuthority(String authority);
}
