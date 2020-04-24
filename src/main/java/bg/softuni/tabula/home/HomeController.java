package bg.softuni.tabula.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "home/home";
  }

  @GetMapping("/home")
  public String homeAbsolute() {
    return home();
  }

  @PostMapping("/home")
  public String homePost() {
    return "redirect:/home";
  }

}
