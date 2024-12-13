package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.NotificationDTO;
import hcmute.fit.event_management.entity.Account;
import hcmute.fit.event_management.entity.Notification;
import hcmute.fit.event_management.repository.AccountRepository;
import hcmute.fit.event_management.repository.NotificationRepository;
import hcmute.fit.event_management.service.INotification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotification {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public <S extends Notification> List<S> findAll(Example<S> example) {
        return notificationRepository.findAll(example);
    }

    @Override
    public <S extends Notification> List<S> saveAll(Iterable<S> entities) {
        return notificationRepository.saveAll(entities);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> findAllById(Iterable<Integer> integers) {
        return notificationRepository.findAllById(integers);
    }

    @Override
    public <S extends Notification> S save(S entity) {
        return notificationRepository.save(entity);
    }

    @Override
    public Optional<Notification> findById(Integer integer) {
        return notificationRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return notificationRepository.existsById(integer);
    }

    @Override
    public long count() {
        return notificationRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        notificationRepository.deleteById(integer);
    }

    @Override
    public List<Notification> findAll(Sort sort) {
        return notificationRepository.findAll(sort);
    }

    @Override
    public Page<Notification> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }
    @Override
    public List<Notification> findByAccount(Account account) {
        return notificationRepository.findByAccount(account);
    }


    @Override
    public Notification createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationDTO, notification);
        notification.setRead(false);
        notification.setAccount(accountRepository.findById(notificationDTO.getAccountID()).orElse(new Account()));
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }
    @Override
    public void markAsRead(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
    @Override
    public void markAllAsRead(int userId) {
        Account account = accountRepository.findById(userId).orElse(new Account());
        List<Notification> notifications = findByAccount(account);
        for (Notification notification : notifications) {
            notification.setRead(true);
        }
        notificationRepository.saveAll(notifications);
    }
}
