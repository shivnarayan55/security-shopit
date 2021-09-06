package com.org.security.request;

public class UpdateUserRequest {
	
	
	private String authority;
	private String roleId;
	private String userId;
	private String email;
	private String username;
	private String name;
	
	public UpdateUserRequest(String authority, String roleId, String userId, String email, String username,
			String name) {
		super();
		this.authority = authority;
		this.roleId = roleId;
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.name = name;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UpdateUserRequest [authority=" + authority + ", roleId=" + roleId + ", userId=" + userId + ", email="
				+ email + ", username=" + username + ", name=" + name + "]";
	}
	
	
	
	
	
	
	
	

}
