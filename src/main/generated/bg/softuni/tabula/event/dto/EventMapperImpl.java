package bg.softuni.tabula.event.dto;

import bg.softuni.tabula.event.model.EventEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T19:44:44+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
public class EventMapperImpl implements EventMapper {

    @Override
    public EventEntity mapEventDtoToEntity(EventDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EventEntity eventEntity = new EventEntity();

        eventEntity.setTitle( dto.getTitle() );
        eventEntity.setDescription( dto.getDescription() );
        eventEntity.setEventType( dto.getEventType() );

        return eventEntity;
    }
}
