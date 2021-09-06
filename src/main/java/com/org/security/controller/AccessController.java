package com.org.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.org.security.model.Resource;
import com.org.security.model.ResourcePerm;
import com.org.security.model.ResourcePermission;
import com.org.security.model.Role;
import com.org.security.model.RolePermission;
import com.org.security.model.User;
import com.org.security.model.UserRole;
import com.org.security.repository.RoleRepository;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;
import com.org.security.service.AccessService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/access")

public class AccessController {
	


	@Autowired
	private AccessService accessService;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	

	@PostMapping("/addResource")
//	@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public Resource addResource(@RequestBody Resource resource) {

		return accessService.addResource(resource);
	}
	
	
	//DELTE RESOURCE BY ID
	@DeleteMapping("/deleteResource/{id}")
//	@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public String deleteResource(@PathVariable int id) {

		return accessService.deleteResource(id);
	}

	@PostMapping("/grantPerm")
//	@PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
	public RolePermission grantPermissions(@RequestBody RolePermission rolePermission) {

		return accessService.grantPermissions(rolePermission);
	}

	@PostMapping("/addRole")
	public Role addRole(@RequestBody Role role) {

		return accessService.addRole(role);

	}
	
	@PostMapping("/setRole")
	public UserRole setRole(@RequestBody UserRole userRole) {
		

		
		
		Long id = Long.parseLong(userRole.getUserId());
		
		Optional<User> user =  userRepository.findById(id);
		Role role = new Role();
		int i = Integer.parseInt(userRole.getRoleId());
		role = roleRepository.findById(i).get();
		String roleName = role.getRoleName();
		
		
		
		User user2 = new User();
		user2 = user.get();
		
		user2.setAuthority(roleName);
		
		userRepository.save(user2);
		

		
		 
		return accessService.setRole(userRole);

	}
	
	//DELETE ROLE BY ID
	@DeleteMapping("/deleteRole/{id}")
	public String deleteRole(@PathVariable int id) {

		return accessService.deleteRole(id);

	}
	
	@GetMapping("/getUserRole")
	public List<UserRole> getUserRole() {
		return userRoleRepository.findAll();
	}
	
	
	
	

	
	
	
	@GetMapping("/getroleByRoleId/{roleID}")
	public Role getRoleByRoleID(@PathVariable int roleID) {

		return accessService.getRoleByRoleID(roleID);
	}

	@GetMapping("/getByRoleName/{roleName}")
	public Role getByRoleName(@PathVariable String roleName) {

		return accessService.getByRoleName(roleName);
	}
	
	@GetMapping("/viewAllResources")
	public List<Resource> viewAllResources(){
		return accessService.viewAllResources();
	}

	@PostMapping("/createRolewithPerm")
	public String createRoleWithPermissions(@RequestBody ResourcePerm resourcePerm) {

		System.out.println("inside ");
		Role role = new Role();

		role.setRoleName(resourcePerm.getRoleName());

		Role roledata = accessService.addRole(role);

		System.out.println(roledata.getRoleID());
		System.out.print(resourcePerm.getPermissionList());

		for (RolePermission p : resourcePerm.getPermissionList()) {

			p.setRoleId(roledata.getRoleID());
		}

		System.out.println(resourcePerm.getPermissionList());
		for (RolePermission p : resourcePerm.getPermissionList()) {
			accessService.grantPermissions(p);
		}
		return "successFully created";

	}

	

	
//	  @GetMapping("/getPermissionsByRoleName/{roleName}")
//	  public ResourcePerm getPermissionsByRoleName(@PathVariable String roleName){
//		  
//		  Role role=getByRoleName(roleName);
//		  
//		  List<RolePermission> rolepermlist=  accessService.getPermissionsByRoleId(role.getRoleID());
//		  
//		  ResourcePerm resourcePerm=new ResourcePerm();
//		  
//		  resourcePerm.setRoleName(roleName);
//		  resourcePerm.setPermissionList(rolepermlist);
//		  
//		  
//	  return resourcePerm;
//	  }
	 
	  

	@GetMapping("/getpermissions/{roleID}")
	public List<RolePermission> getPermissionsByRoleId(@PathVariable int roleID) {

		return accessService.getPermissionsByRoleId(roleID);
	}
	
	
	@GetMapping("/viewAllRoles")
	public List<Role> getAllRoles(){
		return accessService.getAllRoles();
	}


    
	@DeleteMapping("/deleteX/{id}")
	 public String deleteRoleById(Integer roleId) {
		return accessService.deleteRoleById(roleId);
	}
	
	@GetMapping("/viewAllRolePermissions")
	public List<RolePermission> viewallRolePermissions(){
		return accessService.viewAllRolePermissions();
	}
	
	
	
	
	
	
}
