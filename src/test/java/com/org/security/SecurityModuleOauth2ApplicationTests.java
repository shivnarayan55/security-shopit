package com.org.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;

import com.org.security.controller.AuthRestAPIs;
import com.org.security.jwt.JwtProvider;
import com.org.security.model.CustomUser;
import com.org.security.model.Resource;
import com.org.security.model.Role;
import com.org.security.model.RolePermission;
import com.org.security.model.User;
import com.org.security.model.UserEntity;
import com.org.security.model.UserRole;
import com.org.security.repository.AccessRepository;
import com.org.security.repository.PermissionRepository;
import com.org.security.repository.RolePermissionRepository;
import com.org.security.repository.RoleRepository;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;
import com.org.security.request.UpdateUserRequest;
import com.org.security.service.AccessService;
import com.org.security.service.CustomUserDetailsService;
import com.org.security.service.UserService;

@SpringBootTest
class SecurityModuleOauth2ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;

    @Autowired
    AuthRestAPIs authRestAPIs;



    @Autowired
    UserService userService;

    @Autowired
    AccessService accessService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserRoleRepository userRoleRepository;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    AccessRepository accessRepository;

    @MockBean
    PermissionRepository permissionRepository;

    @MockBean
    RolePermissionRepository rolePermissionRepository;

    @MockBean
    CustomUserDetailsService customUserDetailsService;


	
	
	
	
	
	@Test
	public void findAllUsers(){
		
		List<User> user = userRepository.findAll();

		when(user).thenReturn(Stream.of(new User(21L, "Admin", "Test", "TestName", "password"), new User(31L, "Superadmin", "Test", "TestName", "password")).collect(Collectors.toList()));
		assertEquals(2, userService.getAllUsers().size() );

	}
	
	  @Test
	    public void updateUserTest() {
		 
	        
	       
	        UserRole userRole = new UserRole();
			userRole.setId(1L);
			userRole.setRoleId("1");
			userRole.setUserId("1");
//			assertEquals(userRole,userRoleRepository.save(userRole));
			 when(userRoleRepository.save(userRole)).thenReturn(userRole);
		        assertEquals(userRole, userRoleRepository.save(userRole));
		        
		        User userResponse = new User(1L, "Test User", "password", "testuser@gmail.com", "password");
		        UpdateUserRequest user = new UpdateUserRequest("Superadmin","1", "1", "testuser@gmail.com", "shiv25", "shiv");
		        when(userRepository.save(userResponse)).thenReturn(userResponse);
		        assertEquals(userResponse, userRepository.save(userResponse));
		     
	    }

	    @Test
	    public void deleteUserTest() {
	        long userId = 1;
	        String response = "User Deleted";

	        assertEquals(response, userService.deleteById(userId));

	    }

	    @Test
	    public void addResourceTest() {
	        Resource resource = new Resource();
	        resource.setId(1);
	        resource.setApiUrl("/api/test");
	        resource.setResourceName("test");

	        when(accessRepository.save(resource)).thenReturn(resource);
	        assertEquals(resource, accessService.addResource(resource));
	    }

	    @Test
	    public void addRoleTest() {
	        Role role = new Role();
	        role.setRoleID(1);
	        role.setRoleName("testRole");
	        when(roleRepository.save(role)).thenReturn(role);
	        assertEquals(role, accessService.addRole(role));
	    }

	    @Test
	    public void grantPermissionTest() {
	        RolePermission rolePermission = new RolePermission(1, 1, 1, true, true, true, true);
	        when(permissionRepository.save(rolePermission)).thenReturn(rolePermission);
	        assertEquals(rolePermission, accessService.grantPermissions(rolePermission));

	    }
	    
	    @Test
	    public void getPermissionsByRoleIdTest() {
	    	int roleId = 1;
	    	when(permissionRepository.findByroleId(roleId)).thenReturn(Stream.of(new RolePermission(1, 1, 1, true, true, true, true)).collect(Collectors.toList()));
	    	assertEquals(1, accessService.getPermissionsByRoleId(roleId).size());
	    }


	    @Test
	    public void grantPermissionsByRoleId() {
	        
	        
	        RolePermission rolePermission = new RolePermission(1, 1, 1, true, true, true, true);
	        when(permissionRepository.save(rolePermission)).thenReturn(rolePermission);

	        assertEquals(rolePermission, accessService.grantPermissions(rolePermission));
	    }

	    @Test
	    public void getRoleByRoleIdTest() {
	        int roleId = 1;
	        Role role = new Role(1, "RoleName");
	        when(roleRepository.findByroleID(roleId)).thenReturn(role);

	        assertEquals(role, accessService.getRoleByRoleID(roleId));
	    }

	    @Test
	    public void getByRoleNameTest() {
	        String roleName = "admin";
	        Role role = new Role(2, "admin");

	        when(roleRepository.findByroleName(roleName)).thenReturn(role);

	        assertEquals(role, accessService.getByRoleName(roleName));
	    }

//		@Test
//		public void updatepermissionsByRoleIDAndResourceIdTest(){
//			RolePermission rp = new RolePermission(1,1,1, true, true, true, true);
//			String response = "success";
//			ResourcePerm resourcePerm = new ResourcePerm("testRole", Stream.of(new RolePermission(1,1,1, true, true, true, true), new RolePermission(1, 1, 2, true, true, true, true)).collect(Collectors.toList()));
//	
//			when(permissionRepository.save(rp)).thenReturn(rp);
//	
//			assertEquals(response, accessService.updatepermissionsByRoleIDAndResourceId(resourcePerm));
//		}

	    @Test
	    public void deleteResourceTest() {
	        String response = "Deleted";
	        int id = 1;
	        assertEquals(response, accessService.deleteResource(id));
	    }

	    @Test
	    public void deleteRoleTest() {
	        String response = "Deleted role";
	        int id = 1;

	        assertEquals(response, accessService.deleteRole(id));
	    }

	    @Test
	    public void setRoleTest() {

	        UserRole userRole = new UserRole(1L, "1", "1");

	        when(userRoleRepository.save(userRole)).thenReturn(userRole);

	        assertEquals(userRole, accessService.setRole(userRole));

	    }

	    @Test
	    public void getAllRolesTest() {
	        when(roleRepository.findAll()).thenReturn(Stream.of(new Role(1, "TestRole"), new Role(2, "TestRole2")).collect(Collectors.toList()));

	        assertEquals(2, accessService.getAllRoles().size());
	    }


	    @Test
	    public void deleteRoleById() {
	        int roleId = 1;
	        String response = "Deleted Role";

	        assertEquals(response, accessService.deleteRoleById(roleId));
	    }


	    @Test
	    public void viewAllRolePermissionsTest() {
	        when(rolePermissionRepository.findAll()).thenReturn(Stream.of(new RolePermission(1, 1, 1, true, true, true, true)).collect(Collectors.toList()));

	        assertEquals(1, accessService.viewAllRolePermissions().size());
	    }

	    @Test
	    public void viewAllResourcesTest() {
	        when(accessRepository.findAll()).thenReturn(Stream.of(new Resource(1, "TestResource", "/api/test")).collect(Collectors.toList()));

	        assertEquals(1, accessService.viewAllResources().size());
	    }

	    @Test
	    public void getAllUserRolesTest() {
	        when(userRoleRepository.findAll()).thenReturn(Stream.of(
	                new UserRole(1L, "1", "1"), new UserRole(1L, "2", "2")
	        ).collect(Collectors.toList()));

	        assertEquals(2, userService.viewAllUserRoles().size());
	    }

	    @Test
	    public void viewUserByIdTest() {
	        Long id = 1L;
	        User user = new User(1L, "Superadmin", "testUser", "testUser@gmail.com","shiv", "password");
	        when(userRepository.findById(id)).thenReturn(Optional.of(user));

	        assertEquals(user, userService.viewUserById(id));
	    }


//	    @Test
//	    public void loadByUsernameTest() {
//	        UserEntity userEntity = new UserEntity();
//	        userEntity.setEmailId("TEST@123.com");
//	        userEntity.setId("1");
//	        userEntity.setName("TestName");
//	
//	        String username = "Test";
//	
//	        CustomUser customUser = new CustomUser(userEntity);
	
//			assertTrue(userEntity.getId()!=null);
//	        assertEquals(customUser, customUserDetailsService.loadUserByUsername(username));
//	    }

//		@Test
//		public void authenticateUserTest(){
//			LoginForm loginForm = new LoginForm();
//			loginForm.setPassword("password");
//			loginForm.setUsername("test@hyperfounder.io");
//			Object usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
	//
////			Authentication authentication = authenticationManager.authenticate((Authentication) usernamePasswordAuthenticationToken);
//			
////			String jwt = jwtProvider.generateJwtToken(authentication);
	////
////			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	//
//	        User user = new User();
//	        user.setAuthority("Admin");
//	        user.setEmail(loginForm.getUsername());
//	        user.setUsername("TestUser");
//	        user.setPassword("password");
//	        user.setId(1L);
	//
//	       JwtResponse jwtResponse = new JwtResponse(user, jwt, userDetails.getUsername(), userDetails.getAuthorities());
	//
//	       String email = "test@gmail.com";
	//
//	       when(userRepository.findByEmail(email)).thenReturn(Stream.of(new User(1L, "TestUser","TestUser@hyperfounder.io","TestUser","password")).collect(Collectors.toList()));
	//
	//
//			
//			when(authenticationManager.authenticate((Authentication) usernamePasswordAuthenticationToken)).thenReturn(authentication);
//			
//			when(jwtProvider.generateJwtToken(authentication)).thenReturn(jwt);
	//	
//			when(authentication.getPrincipal()).thenReturn(userDetails);
	//
//			assertEquals(jwtResponse, authRestAPIs.authenticateUser(loginForm));
//		}

	


}
