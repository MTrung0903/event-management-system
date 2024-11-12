package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.ProviderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderEventRepository extends JpaRepository<ProviderEvent, Integer> {
}
