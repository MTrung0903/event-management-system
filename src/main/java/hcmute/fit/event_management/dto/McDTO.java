package hcmute.fit.event_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class McDTO {
    private int mcID;
    private String mcName;
    private String email;
    private List<EventDTO> listEvents;
}
