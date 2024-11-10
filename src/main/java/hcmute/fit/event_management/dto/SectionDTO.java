package hcmute.fit.event_management.dto;

import hcmute.fit.event_management.entity.DetailSection;
import hcmute.fit.event_management.entity.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {
    private int id;
    private Date startTime;
    private Date endTime;
    private int eventId;

    private List<DetailSectionDTO> listDetailSections;
}
