package bg.softuni.tabula.announcement;

import bg.softuni.tabula.announcement.dto.AnnouncementDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

  private final AnnouncementService announcementService;

  @Autowired
  public AnnouncementController(AnnouncementService announcementService) {

    this.announcementService = announcementService;
  }

  @GetMapping
  public String announcement(Model model) {
    model.addAttribute("active", "announcements");
    model.addAttribute("announcements",
        announcementService.findAll());
    return "announcement/announcements";
  }

  @GetMapping("/new")
  public String newAnnouncement(Model model) {
    model.addAttribute("active", "announcements");
    model.addAttribute("formData", new AnnouncementDTO());
    return "announcement/new";
  }

  @PostMapping("/save")
  public String save(@Valid @ModelAttribute("formData") AnnouncementDTO announcementDTO,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "announcement/new";
    }

    announcementService.udpateOrCreateAnnouncement(announcementDTO);

    return "redirect:/announcements";
  }

  @DeleteMapping("/delete")
  public String delete(@ModelAttribute(name="deleteId") Long announcementId) {

    announcementService.deleteAnnouncement(announcementId);

    return "redirect:/announcements";
  }

}
