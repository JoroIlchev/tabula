package bg.softuni.tabula.subscription;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubscriptionController {

  @GetMapping("/subscriptions")
  public String announcement(Model model) {
    model.addAttribute("active", "subscriptions");
    return "subscription/subscriptions";
  }

}
