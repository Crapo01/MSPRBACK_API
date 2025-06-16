package com.capus.securedapi.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	@Schema( description = "String between 3 and 20 char.", example = "editor")
  private String username;

	@NotBlank
	@Schema(description = "complex password", example = "tIFQ4?&eh")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
