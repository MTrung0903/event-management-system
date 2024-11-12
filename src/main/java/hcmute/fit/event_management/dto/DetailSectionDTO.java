package hcmute.fit.event_management.dto;

import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.entity.Section;
import hcmute.fit.event_management.entity.Speaker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailSectionDTO {
    private int id;
    private String speakerTitle;
    private int speakerId;
    private int eventId;
    private int sectionId;
}
