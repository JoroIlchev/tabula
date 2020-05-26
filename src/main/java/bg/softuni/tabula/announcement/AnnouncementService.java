package bg.softuni.tabula.announcement;

import bg.softuni.tabula.announcement.dto.AnnouncementDTO;
import bg.softuni.tabula.announcement.dto.AnnouncementMapper;
import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnnouncementService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementService.class);

  private final AnnouncementRepository announcementRepository;


  public void updateOrCreateAnnouncement(AnnouncementDTO announcementDTO) {

    LOGGER.debug("Creating or updating announcement.");

    AnnouncementEntity announcement = AnnouncementMapper.INSTANCE.mapAnnouncementDtoToEntity(announcementDTO);

    announcement.setCreatedOn(Instant.now());
    announcement.setUpdatedOn(Instant.now());

    announcementRepository.save(announcement);
  }

  public void deleteAnnouncement(Long announcementId) {

    LOGGER.debug("Deleting announcement by ID: {}.", announcementId);

    announcementRepository.deleteById(announcementId);
  }

  public List<AnnouncementDTO> findAll() {
    // todo pageable
    return announcementRepository.
        findAll().
        stream().
        map(AnnouncementMapper.INSTANCE::mapAnnouncementEntityToDto).
        collect(Collectors.toList());
  }
}
