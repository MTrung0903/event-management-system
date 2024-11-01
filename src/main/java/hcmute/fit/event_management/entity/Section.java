package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "section")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @Column(name = "section_id")
    private int id;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
