package com.jquispeluyo.oauth2server.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class Usuario {

	@Id
	private String id;
	private String firstname;
	private String lastname;
	@Indexed(unique = true)
	private String username;
	private String password;
	private Boolean enabled;
	private List<String> roles;

}
