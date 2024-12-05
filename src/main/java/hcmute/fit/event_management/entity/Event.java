package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventID;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "event_location")
    private String eventLocation;
    @Column(name = "event_host")
    private String eventHost;
    @Column(name = "event_desc")
    private String eventDescription;
    @Column(name = "event_image")
    private String eventImg;
    @Column(name = "event_start")
    private Date eventStart;
    @Column(name = "event_end")
    private Date eventEnd;
    @Column(name = "event_status")
    private String eventStatus;
    @ManyToOne
    @JoinColumn(name = "man_id")
    private Manager manager;
    @ManyToOne
    @JoinColumn(name = "mc_id")
    private Mc mc;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Invite> listInvites;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<ProviderEvent> listProviderEvents;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Section> listSections;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<SponsorEvent> listSponsorEvents;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Task> listTasks;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Team> listTeams;
}
