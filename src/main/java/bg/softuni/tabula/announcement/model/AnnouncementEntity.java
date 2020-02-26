package bg.softuni.tabula.announcement.model;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="announcements")
public class AnnouncementEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column
  private Instant createdOn;

  @NotNull
  @Column
  private Instant updatedOn;

  @NotNull
  @Column
  private String title;

  @NotNull
  @Column
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
    AnnouncementEntity that = (AnnouncementEntity) o;
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
    return "Announcement{" +
        "id=" + id +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
