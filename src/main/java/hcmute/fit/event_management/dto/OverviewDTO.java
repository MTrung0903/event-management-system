package hcmute.fit.event_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverviewDTO {
    private int totalEvents;
    private int totalDevices;
    private int totalEmployees;
    private int[] cntCompleted;
    private int[] cntCancel;
    private int[] cntSponsor;
    private List<EventDTO> listEvent;
}
