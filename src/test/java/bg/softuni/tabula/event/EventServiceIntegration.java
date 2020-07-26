package bg.softuni.tabula.event;

import bg.softuni.tabula.event.dto.CalendarWeekDTO;
import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.model.EventType;
import bg.softuni.tabula.event.repository.EventRepository;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EventServiceIntegration {

  @Autowired
  EventsService eventService;

  @Autowired
  EventRepository eventRepository;

  private EventEntity testEventEntity;

  private LocalDateTime eventOccurrence =
      LocalDateTime.of(2020, 7, 3, 12, 23, 0);

  @BeforeEach
  public void setUp() {

    eventRepository.deleteAll();

    testEventEntity = new EventEntity();
    testEventEntity.setEventType(EventType.SINGLE);
    testEventEntity.setOccurrence(eventOccurrence.atZone(ZoneId.systemDefault()).toInstant());
    testEventEntity.setDescription("Test event description");
    testEventEntity.setTitle("Test event title");

    eventRepository.save(testEventEntity);
  }

  @AfterEach
  public void tearDown() {
    eventRepository.deleteAll();
  }

  @Test
  public void testEventRetrieved() {
    List<CalendarWeekDTO> eventDTOList = eventService.
        getEventsForMonth(YearMonth.of(eventOccurrence.getYear(), eventOccurrence.getMonth()));

    EventDTO eventDTO = eventDTOList.get(0).getDays().get(4).getEvents().get(0);

    Assertions.assertEquals(testEventEntity.getTitle(), eventDTO.getTitle());
    Assertions.assertEquals(testEventEntity.getDescription(), eventDTO.getDescription());
  }
}
