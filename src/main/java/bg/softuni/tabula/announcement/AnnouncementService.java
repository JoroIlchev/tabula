package bg.softuni.tabula.announcement;

import bg.softuni.tabula.announcement.dto.AnnouncementDTO;
import bg.softuni.tabula.announcement.dto.AnnouncementMapper;
import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

  private final AnnouncementRepository announcementRepository;

  @Autowired
  public AnnouncementService(AnnouncementRepository announcementRepository) {

    this.announcementRepository = announcementRepository;
  }

  public void udpateOrCreateAnnouncement(AnnouncementDTO announcementDTO) {
    AnnouncementEntity announcement = AnnouncementMapper.INSTANCE.mapAnnouncementDtoToEntity(announcementDTO);

    announcement.setCreatedOn(Instant.now());
    announcement.setUpdatedOn(Instant.now());

    announcementRepository.save(announcement);
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
