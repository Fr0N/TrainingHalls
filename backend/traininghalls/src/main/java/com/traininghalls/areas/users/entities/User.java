package com.traininghalls.areas.users.entities;

import com.traininghalls.areas.roles.entities.Role;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private List<Role> authorities;

    @Column(columnDefinition = "DECIMAL")
    private BigDecimal balance;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    public User() {
        this.setAuthorities(new ArrayList<Role>());
    }

    @Override
    public Collection<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Role role){
        this.getAuthorities().add(role);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
