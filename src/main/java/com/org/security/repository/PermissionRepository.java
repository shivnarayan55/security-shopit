package com.org.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.org.security.model.RolePermission;


@Repository
public interface PermissionRepository extends JpaRepository<RolePermission,Integer> {

	List<RolePermission> findByroleId(int roleId);

	RolePermission  findByRoleIdAndResourceId(int roleId, int resourceId);

	
	//RolePermission findByRoleName(int roleId);

	//public void findByRoleName();
	
	//List<RolePermission> findByroleName(String roleName);

	
	
}
