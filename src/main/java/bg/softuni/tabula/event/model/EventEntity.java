package bg.softuni.tabula.event.model;

import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="events")
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column
  private String title;

  @NotNull
  @Column
  private String description;

  @NotNull
  @Column(name="event_type")
  @Enumerated(EnumType.STRING)
  private EventType eventType;

  @NotNull
  @Column(name="occurrence")
  private Instant occurrence;

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

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Instant getOccurrence() {
    return occurrence;
  }

  public void setOccurrence(Instant nextOccurrence) {
    this.occurrence = nextOccurrence;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventEntity that = (EventEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(title, that.title) &&
        Objects.equals(description, that.description) &&
        eventType == that.eventType &&
        Objects.equals(occurrence, that.occurrence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, eventType, occurrence);
  }
}
