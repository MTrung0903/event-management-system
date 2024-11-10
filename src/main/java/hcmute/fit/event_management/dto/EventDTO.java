package hcmute.fit.event_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private int eventId;
    private String eventName;
    private String eventDescription;
    private String eventImg;
    private String eventDate;
    private String eventLocation;
    private String eventDetail;
    private ManagerDTO manager;
    private List<InviteDTO> listInvites;
}
