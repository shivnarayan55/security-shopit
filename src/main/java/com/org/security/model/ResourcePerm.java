package com.org.security.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourcePerm {
	
	@Override
	public String toString() {
		return "ResourcePerm [RoleName=" + RoleName + ", PermissionList=" + PermissionList + "]";
	}

	private String RoleName;
	
	
    List<RolePermission>  PermissionList;


	public String getRoleName() {
		// TODO Auto-generated method stub
		return RoleName;
	}


	public List<RolePermission> getPermissionList() {
		// TODO Auto-generated method stub
		return PermissionList;
	}


	public void setRoleName(String RoleName) {
		this.RoleName = RoleName;
	}
	
	public void setPermissionList(List<RolePermission> PermissionList) {
		this.PermissionList = PermissionList;
	}
	
	
}
