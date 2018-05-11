package com.traininghalls.areas.users.servicesImpl;

import com.traininghalls.areas.roles.services.RoleService;
import com.traininghalls.areas.users.entities.User;
import com.traininghalls.areas.users.models.RegisterUserBindingModel;
import com.traininghalls.areas.users.repositories.UserRepository;
import com.traininghalls.areas.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("500");

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }

    @Override
    public boolean register(RegisterUserBindingModel registerUserBindingModel) {

        User user = this.modelMapper.map(registerUserBindingModel, User.class);

        user.setPassword(this.bCryptPasswordEncoder.encode(registerUserBindingModel.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.addAuthority(this.roleService.getDefaultRole());
        user.setBalance(INITIAL_BALANCE);

        this.userRepository.save(user);

        return true;
    }

    @Override
    public boolean userExists(String username) {
        User user = this.userRepository.findByUsername(username);

        return user != null;
    }
}
