package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Integer> {
}
