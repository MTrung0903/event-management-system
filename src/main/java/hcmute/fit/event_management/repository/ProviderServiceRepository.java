package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.ProviderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderServiceRepository extends JpaRepository<ProviderService, Integer> {

    @Query("select p from ProviderService p where p.provider.id = :id")
    List<ProviderService> findByProviderId(int id);
}
