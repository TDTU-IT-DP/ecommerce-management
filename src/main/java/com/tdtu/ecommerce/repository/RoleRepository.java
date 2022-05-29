package com.tdtu.ecommerce.repository;

import com.tdtu.ecommerce.entity.ERole;
import com.tdtu.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
