package bg.softuni.tabula;

import static java.time.temporal.TemporalAdjusters.next;

import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import bg.softuni.tabula.event.model.EventEntity;
import bg.softuni.tabula.event.model.EventType;
import bg.softuni.tabula.event.repository.EventRepository;
import bg.softuni.tabula.user.RoleEntity;
import bg.softuni.tabula.user.UserEntity;
import bg.softuni.tabula.user.UserRepository;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

    // demo user - admin
    UserEntity adminUser = new UserEntity();
    adminUser.setEmail("lucho@example.com");
    adminUser.setPasswordHash(new BCryptPasswordEncoder().encode("topsecret"));

    RoleEntity adminRoleAdminUser = new RoleEntity();
    adminRoleAdminUser.setRole("ROLE_ADMIN");

    RoleEntity roleUserAminUser = new RoleEntity();
    roleUserAminUser.setRole("ROLE_USER");

    adminUser.setRoles(List.of(adminRoleAdminUser, roleUserAminUser));

    userRepository.save(adminUser);

    // normal user - admin
    UserEntity normalUser = new UserEntity();
    normalUser.setEmail("user@example.com");
    normalUser.setPasswordHash(new BCryptPasswordEncoder().encode("topsecret"));

    RoleEntity roleUserNormalUser = new RoleEntity();
    roleUserNormalUser.setRole("ROLE_USER");

    normalUser.setRoles(List.of(roleUserNormalUser));

    userRepository.save(normalUser);


    AnnouncementEntity welcome = new AnnouncementEntity();
    welcome.setUpdatedOn(Instant.now());
    welcome.setCreatedOn(Instant.now());
    welcome.setTitle("Spring Advanced");
    welcome.setDescription("Welcome to the Spring Advanced Course!");
    announcementRepository.save(welcome);

    // ANNUAL FOR THIS MONTH
    EventEntity annualEvent = new EventEntity();
    annualEvent.setTitle("The annual event happens here!");
    annualEvent.setDescription("This is one little annual event");
    annualEvent.setEventType(EventType.ANNUALLY);
    annualEvent.setOccurrence(getRandomFromCurrentMonth());
    eventRepository.save(annualEvent);

    // MONTHLY
    EventEntity monthlyEvent = new EventEntity();
    monthlyEvent.setTitle("The monthly event happens here!");
    monthlyEvent.setDescription("This is one little monthly event");
    monthlyEvent.setEventType(EventType.MONTHLY);
    monthlyEvent.setOccurrence(getRandomFromCurrentMonth());
    eventRepository.save(monthlyEvent);

    //WEEKLY FOR THE SECOND WEEK
    EventEntity weeklyEvent = new EventEntity();
    weeklyEvent.setTitle("The weekly event happens here!");
    weeklyEvent.setDescription("This is one little weekly event! Feel welcomed!");
    weeklyEvent.setEventType(EventType.WEEKLY);
    weeklyEvent.setOccurrence(getSecondWednesday());
    eventRepository.save(weeklyEvent);

    //SINGLE FOR THE CURRENT DAY
    EventEntity singleEvent = new EventEntity();
    singleEvent.setTitle("The event for today!!!");
    singleEvent.setDescription("This is one little single event! Feel welcomed!");
    singleEvent.setEventType(EventType.SINGLE);
    singleEvent.setOccurrence(Instant.now());
    eventRepository.save(singleEvent);

    //ANOTHER SINGLE FOR THE CURRENT DAY, unless it is around midnight :-)
    EventEntity anotherSingleEvent = new EventEntity();
    anotherSingleEvent.setTitle("Another single event!");
    anotherSingleEvent.setDescription("This is one more little single event! Feel welcomed!");
    anotherSingleEvent.setEventType(EventType.SINGLE);
    anotherSingleEvent.setOccurrence(Instant.now().plusSeconds(60*10));
    eventRepository.save(anotherSingleEvent);
  }

  private Instant getSecondWednesday() {
    LocalDateTime now = LocalDateTime.now().withDayOfMonth(1);

    if (now.getDayOfWeek().getValue() > DayOfWeek.WEDNESDAY.getValue()) {
      now = now.
          with(next(DayOfWeek.WEDNESDAY)).
          with(next(DayOfWeek.WEDNESDAY));
    } else {
      now = now.with(next(DayOfWeek.WEDNESDAY));
    }

    return now.atZone(ZoneId.systemDefault()).
        toInstant();
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
