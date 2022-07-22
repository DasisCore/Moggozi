package com.JJP.restapiserver.repository;

import com.JJP.restapiserver.entity.ERole;
import com.JJP.restapiserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role_name);
}
