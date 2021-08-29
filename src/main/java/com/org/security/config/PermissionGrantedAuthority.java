package com.org.security.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.org.security.model.ResourcePermission;
import com.org.security.model.User;
import com.org.security.model.UserRole;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;



public class PermissionGrantedAuthority implements GrantedAuthority{ 
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserRepository userRepository;

	public PermissionGrantedAuthority(List<ResourcePermission> resourcePermissions) {
		super();
		this.resourcePermissions = resourcePermissions;
	}

	@Override
	public String toString() {
		return "PermissionGrantedAuthority [resourcePermissions=" + resourcePermissions + "]";
	}

	public List<ResourcePermission> getResourcePermissions() {
		return resourcePermissions;
	}

	public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
		this.resourcePermissions = resourcePermissions;
	}

	private List<ResourcePermission> resourcePermissions;

	@Override
	public String getAuthority() {
	
	return "";
      

}


}