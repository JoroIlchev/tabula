package bg.softuni.tabula.event.dto;

import bg.softuni.tabula.event.model.EventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {

  @NotBlank
  private String title;

  @NotBlank
  @Size(min=10, message = "The description should be more than 10 characters.")
  private String description;

  @NotNull
  private EventType eventType;

  @Future
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime eventTime;

  public LocalDateTime getEventTime() {
    return eventTime;
  }

  public void setEventTime(LocalDateTime eventTime) {
    this.eventTime = eventTime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  @Override
  public String toString() {
    return "EventDTO{" +
        "title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", eventType=" + eventType +
        ", eventTime=" + eventTime +
        '}';
  }
}
