package com.capus.securedapi.payload.request;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Schema(description = "valid email address", example = "example@site.com")
  @Email
  private String email;

  // editor and admin role can only be allowed by admins
  @Schema(description = "Set of roles available at signup", allowableValues = {"viewer","none" } ,example = "[\"viewer\"]")
  private Set<String> role;

  @NotBlank
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "8 to 20 characters,at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character:")
  @Schema(description = "complex password", example = "tIFQ4?&eh")
  private String password;

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

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
