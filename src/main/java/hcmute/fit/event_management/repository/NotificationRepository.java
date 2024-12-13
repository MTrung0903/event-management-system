package hcmute.fit.event_management.repository;


import hcmute.fit.event_management.entity.Account;
import hcmute.fit.event_management.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    List<Notification> findByAccount(Account account);
}
