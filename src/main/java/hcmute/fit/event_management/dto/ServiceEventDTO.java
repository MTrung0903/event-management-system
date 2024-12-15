package hcmute.fit.event_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEventDTO {
    private int serviceId;
    private int eventId;
    private String rentalDate;
    private String expDate;
}
