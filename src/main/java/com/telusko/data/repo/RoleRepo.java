package com.telusko.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.data.domain.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
