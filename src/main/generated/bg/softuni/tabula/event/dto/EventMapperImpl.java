package bg.softuni.tabula.event.dto;

import bg.softuni.tabula.event.model.EventEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-10T12:10:03+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class EventMapperImpl implements EventMapper {

    private final InstantMapper instantMapper = new InstantMapper();

    @Override
    public EventEntity mapDtoToEntity(EventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setTitle( dto.getTitle() );
        eventEntity.setDescription( dto.getDescription() );
        eventEntity.setEventType( dto.getEventType() );

        return eventEntity;
    }

    @Override
    public EventDTO mapEntityToDto(EventEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EventDTO eventDTO = new EventDTO();

        eventDTO.setEventTime( instantMapper.asLocalDateTime( entity.getOccurrence() ) );
        eventDTO.setDescription( entity.getDescription() );
        eventDTO.setTitle( entity.getTitle() );
        eventDTO.setEventType( entity.getEventType() );

        return eventDTO;
    }
}
