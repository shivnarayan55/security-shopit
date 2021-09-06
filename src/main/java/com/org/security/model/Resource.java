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
@Entity
public class Resource {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="resource_name")
	private String resourceName;
	
	@Column(name="api_url")
	private String apiUrl;
	//private String resourceDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Resource(int id, String resourceName, String apiUrl) {
		super();
		this.id = id;
		this.resourceName = resourceName;
		this.apiUrl = apiUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resourceName=" + resourceName + ", apiUrl=" + apiUrl + "]";
	}

	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
}
