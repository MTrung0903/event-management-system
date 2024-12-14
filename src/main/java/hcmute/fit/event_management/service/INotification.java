package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.NotificationDTO;
import hcmute.fit.event_management.entity.Account;
import hcmute.fit.event_management.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface INotification {


    <S extends Notification> List<S> findAll(Example<S> example);


    <S extends Notification> List<S> saveAll(Iterable<S> entities);


    List<Notification> findAll();


    List<Notification> findAllById(Iterable<Integer> integers);


    <S extends Notification> S save(S entity);


    Optional<Notification> findById(Integer integer);


    boolean existsById(Integer integer);


    long count();


    void deleteById(Integer integer);


    List<Notification> findAll(Sort sort);


    Page<Notification> findAll(Pageable pageable);

    List<Notification> findByAccount(Account account);

    Notification createNotification(NotificationDTO notificationDTO);

    void markAsRead(int notificationId);

    void markAllAsRead(int userId);
}
