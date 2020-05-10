package bg.softuni.tabula.event.repository;

import bg.softuni.tabula.event.model.EventEntity;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

  @Query("SELECT e from EventEntity e "
      + "WHERE e.occurrence >= :monthStart")
  List<EventEntity> findAllRelevantEvents(
      @Param("monthStart") Instant monthStart);

}
