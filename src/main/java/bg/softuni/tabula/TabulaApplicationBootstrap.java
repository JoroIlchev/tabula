package bg.softuni.tabula;

import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.user.UserEntity;
import bg.softuni.tabula.user.UserRepository;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TabulaApplicationBootstrap implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AnnouncementRepository announcementRepository;

  @Override
  public void run(String... args) {

    // pre-populating the app with some demo data.

    // demo user
    UserEntity lucho = new UserEntity();
    lucho.setEmail("lucho@example.com");
    lucho.setPasswordHash(new BCryptPasswordEncoder().encode("topsecret"));
    userRepository.save(lucho);

    AnnouncementEntity welcome = new AnnouncementEntity();
    welcome.setUpdatedOn(Instant.now());
    welcome.setCreatedOn(Instant.now());
    welcome.setTitle("Spring Advanced");
    welcome.setDescription("Welcome to the Spring Advanced Course!");
    announcementRepository.save(welcome);
  }
}
