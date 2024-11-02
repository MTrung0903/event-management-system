package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.Mc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface McRepository extends JpaRepository<Mc,Integer> {
}
