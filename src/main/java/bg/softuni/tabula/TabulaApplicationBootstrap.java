package bg.softuni.tabula;

import bg.softuni.tabula.user.UserEntity;
import bg.softuni.tabula.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TabulaApplicationBootstrap implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    // a demo user
    UserEntity lucho = new UserEntity();
    lucho.setEmail("lucho@example.com");
    lucho.setPasswordHash(new BCryptPasswordEncoder().encode("topsecret"));
    userRepository.save(lucho);
  }
}
