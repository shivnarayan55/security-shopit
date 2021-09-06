package com.org.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.security.model.Resource;
import com.org.security.model.ResourcePerm;
import com.org.security.model.ResourcePermission;
import com.org.security.model.Role;
import com.org.security.model.RolePermission;
import com.org.security.model.UserRole;
import com.org.security.repository.AccessRepository;
import com.org.security.repository.PermissionRepository;
import com.org.security.repository.RolePermissionRepository;
import com.org.security.repository.RoleRepository;
import com.org.security.repository.UserRoleRepository;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	AccessRepository accessRepository;

	@Autowired
	PermissionRepository permissionRepository;
	
	
	@Autowired
	RolePermissionRepository rolePermissionRepository;


	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	


	public Resource addResource(Resource resource) {
		
		

		return accessRepository.save(resource) ;
	}

	public RolePermission grantPermissions(RolePermission rolePermission) {
		
		

		return permissionRepository.save(rolePermission);

	}


	public Role addRole(Role role) {

		return roleRepository.save(role) ;
	}

	@Override
	public List<RolePermission> getPermissionsByRoleId(int roleId) {

		return permissionRepository.findByroleId( roleId);
	}


	@Override
	public Role getRoleByRoleID(int roleID) {

		return roleRepository.findByroleID(roleID);
	}


	@Override
	public Role getByRoleName(String roleName) {
		System.out.print("Finding by role name");
		return roleRepository.findByroleName(roleName);
	}

//	@Override
//	public String updatepermissionsByRoleIDAndResourceId(ResourcePerm resourcePerm) {
//
//		Role role=getByRoleName(resourcePerm.getRoleName());
//
//		System.out.println(resourcePerm.getPermissionList());
//
//		for(RolePermission p:resourcePerm.getPermissionList()) {
//
//			p.setRoleId(role.getRoleID());
//		}
//
//		System.out.println(resourcePerm);
//
//		for(RolePermission p:resourcePerm.getPermissionList()) {
//
//			RolePermission rp=permissionRepository.findByRoleIdAndResourceId(p.getRoleId(),p.getResourceId());
//
//			rp.setCanView(p.isCanView());
//			rp.setCanEdit(p.isCanEdit());
//			rp.setCanAdd(p.isCanAdd());
//			rp.setCanDelete(p.isCanDelete());
//			System.out.println(rp);
//			permissionRepository.save(rp);
//
//		}
//		return "success";
//	}

	@Override
	public String deleteResource(int id) {
		accessRepository.deleteById(id);
		return "Deleted";
	}

	@Override
	public String deleteRole(int id) {
		roleRepository.deleteById(id);
		return "Deleted role";
	}

	@Override
	public UserRole setRole(UserRole userRole) {
		return userRoleRepository.save(userRole);
		
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public String deleteRoleById(int roleId) {
		// TODO Auto-generated method stub
		rolePermissionRepository.deleteById(roleId);
		 
		 return "Deleted Role";
		
	}

	@Override
	public List<Resource> viewAllResources() {
		return accessRepository.findAll();
	}
	
	@Override 
	public List<RolePermission> viewAllRolePermissions(){
		return rolePermissionRepository.findAll();
	}



}
