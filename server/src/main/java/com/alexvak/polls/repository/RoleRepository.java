package com.alexvak.polls.repository;

import com.alexvak.polls.domain.Role;
import com.alexvak.polls.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
