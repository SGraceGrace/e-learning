package com.project.elearning.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{

	@Id
	private String id;
	
	@Field("userId")
	private String userId;
	
	@Field("first_name")
	private String firstName;
	
	@Field("last_name")
	private String lastName;
	
	@Field("bio")
	private String bio;
	
	@Field("profession")
	private String profession;
	
	@Field("organisation")
	private String organisation;

	//maintaining as a unique identifier
	@Field("email")
	private String email;

	@JsonIgnore
	@Field("password")
	private String password;

	@JsonIgnore
	@Field("roles")
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}