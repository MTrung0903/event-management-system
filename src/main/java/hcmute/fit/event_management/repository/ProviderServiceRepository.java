package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderServiceRepository extends JpaRepository<ProviderService, Integer> {
}
