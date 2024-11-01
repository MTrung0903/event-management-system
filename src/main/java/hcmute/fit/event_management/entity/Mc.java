package hcmute.fit.event_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="mc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mc {
    @Id
    @Column(name = "mc_id")
    private int mcID;
    @Column(name = "full_name")
    private String mcName;
    @Column(name = "email")
    private String email;

}
