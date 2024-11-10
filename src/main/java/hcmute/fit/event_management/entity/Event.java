package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int evetID;
    @Column(name = "event_name")
    private String eventName;
    @Column(name = "event_desc")
    private String eventDescription;
    @Column(name = "event_image")
    private String eventImg;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "eventlocation")
    private String eventLocation;
    @Column(name = "event_detail")
    private String eventDetail;

    @ManyToOne
    @JoinColumn(name = "man_id")
    private Manager manager;
    @ManyToOne
    @JoinColumn(name = "mc_id")
    private Mc mc;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<DetailSection> listDetailSections;
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
