package bg.softuni.tabula.event.dto;

import bg.softuni.tabula.event.model.EventEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-11T17:55:27+0300",
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

        eventEntity.setOccurrence( instantMapper.asInstant( dto.getEventTime() ) );
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

    @Override
    public EventDTO copy(EventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        EventDTO eventDTO1 = new EventDTO();

        eventDTO1.setEventTime( eventDTO.getEventTime() );
        eventDTO1.setDescription( eventDTO.getDescription() );
        eventDTO1.setTitle( eventDTO.getTitle() );
        eventDTO1.setEventType( eventDTO.getEventType() );

        return eventDTO1;
    }
}
