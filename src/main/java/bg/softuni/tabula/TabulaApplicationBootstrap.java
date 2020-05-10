package bg.softuni.tabula;

import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.model.EventType;
import bg.softuni.tabula.event.repository.EventRepository;
import bg.softuni.tabula.user.UserEntity;
import bg.softuni.tabula.user.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import org.apache.tomcat.jni.Local;
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

  @Autowired
  private EventRepository eventRepository;

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

    // ANNUAL FOR THIS MONTH
    EventEntity sampleEvent = new EventEntity();
    sampleEvent.setTitle("The annual event happens here!");
    sampleEvent.setDescription("This is one little annual event");
    sampleEvent.setEventType(EventType.ANNUALLY);
    sampleEvent.setOccurrence(getRandomFromCurrentMonth());
    eventRepository.save(sampleEvent);
  }

  private Instant getRandomFromCurrentMonth() {
    LocalDateTime now = LocalDateTime.now();
    int daysInMonth = now.toLocalDate().lengthOfMonth();
    int randomDay = (new Random()).nextInt(daysInMonth) + 1;

    return now.
        withDayOfMonth(randomDay).
        atZone(ZoneId.systemDefault()).
        toInstant();
  }
}
