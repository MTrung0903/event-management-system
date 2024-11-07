package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity(name = "sponsorship")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SponsorShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sponsor_ship_id")
    private int sponsorShipID;

    @Column(name = "sponsor_ship_level")
    private String level;

    @Column(name = "sponsor_benefit")
    private String benefit;

    @OneToMany(mappedBy = "sponsorship", cascade = CascadeType.ALL)
    private Set<Sponsor> listSponsors;

}
