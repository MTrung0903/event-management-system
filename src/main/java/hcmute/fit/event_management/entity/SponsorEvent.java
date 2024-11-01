package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "sponsor_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SponsorEvent {

    @Id
    @Column(name = "sponsor_id")
    private int sponsorId;

    @Id
    @Column(name = "event_id")
    private int eventId;

    @ManyToOne
    @JoinColumn(name = "sponsor_id", referencedColumnName = "sponsor_id", nullable = false)
    private Sponsor sponsor;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private Event event;


}