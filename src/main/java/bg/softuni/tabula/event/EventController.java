package bg.softuni.tabula.event;

import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.model.EventType;
import java.time.LocalDateTime;
import java.time.YearMonth;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/events")
@Controller
public class EventController {

  private EventsService eventsService;

  public EventController(EventsService eventsService) {
    this.eventsService = eventsService;
  }

  @GetMapping
  public String eventts(Model model) {

    //The truth is that we should take care about the
    //time zone of the user, should be passed in here, but that's too much
    //for the purpose of this project :-)
    //TODO - should come as an arg
    LocalDateTime now = LocalDateTime.now();

    model.addAttribute("active", "events");

    model.addAttribute("weeks", eventsService.
        getEventsForMonth(YearMonth.of(now.getYear(), now.getMonth())));
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
