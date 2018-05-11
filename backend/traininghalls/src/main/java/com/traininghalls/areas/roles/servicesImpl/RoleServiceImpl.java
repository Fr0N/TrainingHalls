package com.traininghalls.areas.roles.servicesImpl;

import com.traininghalls.areas.roles.entities.Role;
import com.traininghalls.areas.roles.repositories.RoleRepository;
import com.traininghalls.areas.roles.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String USER_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        Role defaultRole = this.roleRepository.findOneByAuthority(USER_ROLE);
        if (defaultRole == null) {
            defaultRole = new Role(USER_ROLE);
            this.roleRepository.save(defaultRole);
            Role adminRole = new Role(ADMIN_ROLE);
            this.roleRepository.save(adminRole);
        }

        return defaultRole;
    }

    @Override
    public Role findOneByAuthority(String authority) {
        return this.roleRepository.findOneByAuthority(authority);
    }
}
