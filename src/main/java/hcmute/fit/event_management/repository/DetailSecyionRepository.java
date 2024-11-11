package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.DetailSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSecyionRepository extends JpaRepository<DetailSection,Integer> {
    @Query("select d from DetailSection d where d.event.eventID = :id")
    List<DetailSection> findSectionByEventID(@Param("id")int id);

}
