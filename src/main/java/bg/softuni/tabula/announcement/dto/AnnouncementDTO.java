package bg.softuni.tabula.announcement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementDTO {

  private Long id;

  private Instant createdOn;

  private Instant updatedOn;

  @NotBlank
  private String title;

  @NotBlank
  @Size(min=10, message = "The description should be more than 10 characters.")
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant createdOn) {
    this.createdOn = createdOn;
  }

  public Instant getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Instant updatedOn) {
    this.updatedOn = updatedOn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnnouncementDTO that = (AnnouncementDTO) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(createdOn, that.createdOn) &&
        Objects.equals(updatedOn, that.updatedOn) &&
        Objects.equals(title, that.title) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, updatedOn, title, description);
  }

  @Override
  public String toString() {
    return "AnnouncementDTO{" +
        "id=" + id +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
