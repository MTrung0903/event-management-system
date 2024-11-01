package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "team")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @Column(name = "team_id")
    private int teamId;
    @Column(name = "team_name")
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
