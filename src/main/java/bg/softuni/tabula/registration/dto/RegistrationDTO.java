package bg.softuni.tabula.registration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldMatch(first = "password", second = "confirmPassword")
public class RegistrationDTO {

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String confirmPassword;

  public String getEmail() {
    return email;
  }

  public RegistrationDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public RegistrationDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public RegistrationDTO setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }

  @Override
  public String toString() {
    return "RegistrationDTO{" +
        "email='" + email + '\'' +
        ", password='" + (password != null ? "[PROVIDED]" : "N/A") + "'" +
        ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : "N/A") + '\'' +
        '}';
  }
}
