package bg.softuni.tabula.announcement.dto;

import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-24T11:44:26+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Override
    public AnnouncementEntity mapAnnouncementDtoToEntity(AnnouncementDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AnnouncementEntity announcementEntity = new AnnouncementEntity();

        announcementEntity.setId( dto.getId() );
        announcementEntity.setCreatedOn( dto.getCreatedOn() );
        announcementEntity.setUpdatedOn( dto.getUpdatedOn() );
        announcementEntity.setTitle( dto.getTitle() );
        announcementEntity.setDescription( dto.getDescription() );

        return announcementEntity;
    }

    @Override
    public AnnouncementDTO mapAnnouncementEntityToDto(AnnouncementEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AnnouncementDTO announcementDTO = new AnnouncementDTO();

        announcementDTO.setId( entity.getId() );
        announcementDTO.setCreatedOn( entity.getCreatedOn() );
        announcementDTO.setUpdatedOn( entity.getUpdatedOn() );
        announcementDTO.setTitle( entity.getTitle() );
        announcementDTO.setDescription( entity.getDescription() );

        return announcementDTO;
    }
}
