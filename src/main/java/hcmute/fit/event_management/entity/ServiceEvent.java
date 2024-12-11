package hcmute.fit.event_management.entity;

import hcmute.fit.event_management.entity.keys.ProviderServiceEventId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_event")
public class ServiceEvent {
    @EmbeddedId
    private ProviderServiceEventId id;

    @ManyToOne
    @MapsId("service_id")
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", nullable = false)
    private ProviderService service;

    @ManyToOne
    @MapsId("event_id")
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private Event event;

    @Column(name = "rental_date")
    private Date rentalDate;
    @Column(name = "exp_date")
    private Date expDate;
}
