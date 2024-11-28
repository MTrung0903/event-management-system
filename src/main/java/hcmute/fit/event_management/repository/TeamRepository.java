package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query("select t from Team t where t.event.eventID =:eventId")
    List<Team> findByEventId(@Param("eventId") int eventId);



}
