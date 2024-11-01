package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="mc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mc_id")
    private int mcID;
    @Column(name = "full_name")
    private String mcName;
    @Column(name = "email")
    private String email;

}
