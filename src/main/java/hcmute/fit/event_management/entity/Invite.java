package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "invite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private int id;
    @Column(name = "invite_name")
    private String name;
    @Column(name = "invite_email")
    private String email;
    @Column(name = "invite_date")
    private Date inviteDate;
    @Column(name = "invite_status")
    private String status;

    @OneToOne(mappedBy = "invite")
    private Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
