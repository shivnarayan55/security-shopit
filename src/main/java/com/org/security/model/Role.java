package com.org.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="role")
public class Role {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int roleID;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
//	@Column(name="ROLE_DESCRIPTION")
//	private String roleDescription;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public Role(int roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
	this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + "]";
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

////	public String getRoleDescription() {
////		return roleDescription;
////	}
//
////	public void setRoleDescription(String roleDescription) {
////		this.roleDescription = roleDescription;
////	}
	
	
}
