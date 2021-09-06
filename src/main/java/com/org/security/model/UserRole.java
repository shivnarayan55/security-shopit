package com.org.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	


	@Column(name="user_id")
	private String userId;
	

	@Column(name="role_id")
	private String roleId;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public UserRole(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}


	public UserRole(Long id, String userId, String roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}


	public UserRole() {
		super();
	}


	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	private int userId;
//	
//	
//	private int roleId;
//
//	public UserRole(Long id, int userId, int roleId) {
//		super();
//		this.id = id;
//		this.userId = userId;
//		this.roleId = roleId;
//	}
//
//	public UserRole(int userId, int roleId) {
//		super();
//		this.userId = userId;
//		this.roleId = roleId;
//	}
//
//	public UserRole() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//	public int getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(int roleId) {
//		this.roleId = roleId;
//	}
//
//	@Override
//	public String toString() {
//		return "UserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
//	}
	
	
	
	

}
