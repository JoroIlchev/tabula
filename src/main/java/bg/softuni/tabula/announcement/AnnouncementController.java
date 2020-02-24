package bg.softuni.tabula.announcement;

import bg.softuni.tabula.announcement.dto.AnnouncementDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnnouncementController {

  @GetMapping("/announcements")
  public String announcement(Model model) {
    model.addAttribute("active", "announcements");
    return "announcement/announcements";
  }

  @GetMapping("/announcements/new")
  public String newAnnouncement(Model model) {
    model.addAttribute("active", "announcements");
    model.addAttribute("formData", new AnnouncementDTO());
    return "announcement/new";
  }

}
