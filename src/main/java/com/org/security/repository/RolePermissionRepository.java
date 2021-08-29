package com.org.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.security.model.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

}
