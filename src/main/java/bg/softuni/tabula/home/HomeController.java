package bg.softuni.tabula.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@PreAuthorize("hasRole('USER')")
public class HomeController {

  @GetMapping("/")
  public String home(Model model, @AuthenticationPrincipal User user) {
    model.addAttribute("user", user);
    return "home/home";
  }

  @GetMapping("/home")
  public String homeAbsolute(Model model, @AuthenticationPrincipal User user) {
    return home(model, user);
  }

  @PostMapping("/home")
  public String homePost() {
    return "redirect:/home";
  }

}
