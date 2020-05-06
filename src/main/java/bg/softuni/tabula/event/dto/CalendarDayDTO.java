package bg.softuni.tabula.event.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarDayDTO {

  private int day;
  private boolean empty;
  private List<EventDTO> events;

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public boolean isEmpty() {
    return empty;
  }

  public void setEmpty(boolean empty) {
    this.empty = empty;
  }

  public List<EventDTO> getEvents() {
    return events == null ? Collections.emptyList() : events;
  }

  public void setEvents(List<EventDTO> events) {
    this.events = events;
  }

  public static CalendarDayDTO ofEmpty(){
    CalendarDayDTO calendarDayDTO = new CalendarDayDTO();
    calendarDayDTO.setDay(0);
    calendarDayDTO.setEmpty(true);
    return calendarDayDTO;
  }

  public static CalendarDayDTO ofDay(int day){
    CalendarDayDTO calendarDayDTO = new CalendarDayDTO();
    calendarDayDTO.setDay(day);
    calendarDayDTO.setEmpty(false);
    return calendarDayDTO;
  }
}
