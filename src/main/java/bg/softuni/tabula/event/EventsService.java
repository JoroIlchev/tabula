package bg.softuni.tabula.event;

import bg.softuni.tabula.event.dto.CalendarDayDTO;
import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.dto.EventMapper;
import bg.softuni.tabula.event.dto.CalendarWeekDTO;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.repository.EventRepository;
import java.time.Instant;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

  public static List<CalendarWeekDTO> calculateEventsForMonth(YearMonth monthInYear) {

    List<CalendarWeekDTO> result = new ArrayList<>();
    CalendarWeekDTO currentWeek = new CalendarWeekDTO();

    int daysInMonth = getDaysInMonth(monthInYear);
    int dayInWeek = getFirstDayInWeek(monthInYear);

    // fill with empty cells
    for (int currentWeekDay = 0; currentWeekDay < dayInWeek; currentWeekDay++) {
      currentWeek.addDay(CalendarDayDTO.ofEmpty());
    }

    // fill in days
    for (int day = 1; day <= daysInMonth; day++) {

      // TODO: Fill in events!
      currentWeek.addDay(CalendarDayDTO.ofDay(day));

      dayInWeek = (++dayInWeek) % 7;

      if (dayInWeek == 0) {
        result.add(currentWeek);
        currentWeek = new CalendarWeekDTO();
      }
    }

    // fill in empty
    boolean addCurrentWeek = false;
    for (int weekDay = dayInWeek; weekDay < 7; weekDay++) {
      currentWeek.addDay(CalendarDayDTO.ofEmpty());
      addCurrentWeek = true;
    }

    if (addCurrentWeek) {
      result.add(currentWeek);
    }

    return result;
  }

  private static int getDaysInMonth(YearMonth monthInYear) {
    return (int)ChronoUnit.DAYS.between(
        monthInYear.atDay(1),
        monthInYear.atDay(1).plusMonths(1));
  }

  private static int getFirstDayInWeek(YearMonth monthInYear) {
    return monthInYear.
        atDay(1).
        getDayOfWeek().
        getValue() - 1;
  }

}
