package bg.softuni.tabula.event;

import bg.softuni.tabula.announcement.AnnouncementService;
import bg.softuni.tabula.announcement.dto.AnnouncementMapper;
import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.dto.EventMapper;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.repository.EventRepository;
import java.time.Instant;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventsService.class);

  private final EventRepository eventRepository;

  public EventsService(EventRepository eventRepository) {

    this.eventRepository = eventRepository;
  }

  public void updateOrCreateEvent(EventDTO eventDTO){

    LOGGER.debug("Creating or updating an event.");

    EventEntity eventEntity = EventMapper.INSTANCE.mapEventDtoToEntity(eventDTO);

    // todo recalculate occurence
    eventRepository.save(eventEntity);
  }

  // todo recalculate the next occurrence

  public Optional<Instant> calculateNextOccurrence(EventEntity event) {
    throw new UnsupportedOperationException("Comming soon!");
  }

}
