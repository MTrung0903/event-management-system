package hcmute.fit.event_management.notification;

import hcmute.fit.event_management.dto.NotificationDTO;
import hcmute.fit.event_management.entity.Notification;
import hcmute.fit.event_management.service.INotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class NotificationController {
    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    INotification notificationService;

    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload NotificationDTO notificationDTO) {
        String message = notificationDTO.getMessage();
        System.out.println("Sending message to user " + notificationDTO.getAccountID() + ": " + message);
        notificationService.createNotification(notificationDTO);
        // Gửi thông báo tới người dùng cụ thể
        template.convertAndSendToUser(String.valueOf(notificationDTO.getAccountID()), "/specific", notificationDTO);
    }

}
