package bg.softuni.tabula.event.dto;

import bg.softuni.tabula.announcement.dto.AnnouncementDTO;
import bg.softuni.tabula.announcement.dto.AnnouncementMapper;
import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.event.model.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

  EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

  EventEntity mapEventDtoToEntity(EventDTO dto);
}
