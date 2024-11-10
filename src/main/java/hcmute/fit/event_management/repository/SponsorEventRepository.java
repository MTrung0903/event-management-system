package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.SponsorEvent;
import hcmute.fit.event_management.entity.keys.SponsorEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorEventRepository extends JpaRepository<SponsorEvent, SponsorEventId> {
}
