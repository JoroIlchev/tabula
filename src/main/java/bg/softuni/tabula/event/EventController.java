package bg.softuni.tabula.event;

import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.model.EventType;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/events")
@Controller
public class EventController {

  private EventsService eventsService;

  public EventController(EventsService eventsService) {
    this.eventsService = eventsService;
  }

  @GetMapping(value={"", "/{year}/{month}"})
  public String events(Model model,
      @PathVariable(required = false) Integer year,
      @PathVariable(required = false) Integer month) {

    //The bitter truth is that we should take care about the
    //time zone of the user that should be passed in here, but that's too much
    //for the purpose of this project :-)

    YearMonth yearAndMonth;
    if (year == null || month == null) {
      yearAndMonth = YearMonth.now();
    } else {
      yearAndMonth = YearMonth.of(year, month);
    }

    // the active menu
    model.addAttribute("active", "events");
    // the events, week by week
    model.addAttribute("weeks", eventsService.
        getEventsForMonth(yearAndMonth));

    // the previous and next months
    model.addAttribute("previous", yearAndMonth.minusMonths(1));
    model.addAttribute("next", yearAndMonth.plusMonths(1));

    return "event/events";
  }

  @GetMapping("/new")
  public String newEvent(Model model) {
    model.addAttribute("active", "events");
    model.addAttribute("eventTypes", EventType.values());
    model.addAttribute("formData", new EventDTO());
    return "event/new";
  }

  @PostMapping("/save")
  public String save(
      @Valid @ModelAttribute("formData") EventDTO eventDTO,
      BindingResult bindingResult,
      ModelMap model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("eventTypes", EventType.values());
      return "event/new";
    }

    eventsService.updateOrCreateEvent(eventDTO);

    return "redirect:/events";
  }

}
