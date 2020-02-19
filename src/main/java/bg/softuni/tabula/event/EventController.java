package bg.softuni.tabula.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {

  @GetMapping("/events")
  public String announcement(Model model) {
    model.addAttribute("active", "events");
    return "event/events";
  }

}
