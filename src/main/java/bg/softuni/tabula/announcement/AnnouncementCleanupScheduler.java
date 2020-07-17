package bg.softuni.tabula.announcement;

import static java.time.temporal.ChronoUnit.HOURS;

import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import java.time.Instant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementCleanupScheduler {

  private final AnnouncementRepository announcementRepository;

  public AnnouncementCleanupScheduler(
      AnnouncementRepository announcementRepository) {
    this.announcementRepository = announcementRepository;
  }

  @Scheduled(cron = "0 0 2 * * ?")
  public void cleanUpOldAnnouncements() {
    Instant deleteUpTo = Instant.now().minus(168, HOURS);
    announcementRepository.deleteByUpdatedOnBefore(deleteUpTo);
  }
}
