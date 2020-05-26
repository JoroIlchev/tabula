package bg.softuni.tabula.event.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
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
}
