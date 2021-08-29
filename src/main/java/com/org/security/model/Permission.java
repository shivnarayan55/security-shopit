package com.org.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	
	private int resource_id;
	private int role_id;

	private boolean can_add;
	private boolean can_edit;
	private boolean can_view;
	private boolean can_delete;
	@Override
	public String toString() {
		return "Permission [resource_id=" + resource_id + ", role_id=" + role_id + ", can_add=" + can_add
				+ ", can_edit=" + can_edit + ", can_view=" + can_view + ", can_delete=" + can_delete + "]";
	}
	
	
	

}
