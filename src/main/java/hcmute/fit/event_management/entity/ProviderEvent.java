package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "provider_event")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderEvent {

    @Id
    @Column(name = "provider_id")
    private int providerID;
    @Id
    @Column(name = "event_id")
    private int eventID;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private Event event;


}