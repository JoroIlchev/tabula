package bg.softuni.tabula.event;

import bg.softuni.tabula.event.dto.EventDTO;
import bg.softuni.tabula.event.model.EventType;
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
  public String announcement(Model model) {
    model.addAttribute("active", "events");

    model.addAttribute("weeks", eventsService.
        calculateEventsForMonth(YearMonth.of(2020, 5)));
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
