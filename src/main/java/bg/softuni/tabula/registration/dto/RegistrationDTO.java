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

  @Override
  public String toString() {
    return "RegistrationDTO{" +
        "email='" + email + '\'' +
        ", password='" + (password != null ? "[PROVIDED]" : "N/A") + "'" +
        ", confirmPassword='" + (confirmPassword != null ? "[PROVIDED]" : "N/A") + '\'' +
        '}';
  }
}
