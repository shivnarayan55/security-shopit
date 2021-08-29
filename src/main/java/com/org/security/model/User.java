package com.org.security.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user")
public class User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="Authority")
	private String authority;

   

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Column(name="NAME")
    private String name;

    @Column(name="USERNAME")
    private String username;

    @Email
    @Column(name="EMAIL_ID")
    private String email;
    
    
    @Column(name="PASSWORD", length=65555)
    private String password;
    

   
    


	

	public User(Long id, String name, String username, @Email String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	

	public User(String username, @Email String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}





	public User(String name, String username, @Email String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", authority=" + authority + ", name=" + name + ", username=" + username + ", email="
				+ email + ", password=" + password + "]";
	}

	


	
    
    



    
 
}