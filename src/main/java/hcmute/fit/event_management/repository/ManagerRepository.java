package hcmute.fit.event_management.repository;

import hcmute.fit.event_management.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    List<Manager> findByPhone(String email);
}
