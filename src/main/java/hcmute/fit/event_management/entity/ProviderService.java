package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "provider_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderService {
    @Id
    @Column(name = "service_id")
    private int id;
    @Column(name = "service_type")
    private String serviceType;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "service_desc")
    private String serviceDesc;
    @Column(name = "service_price")
    private String price;
    @Column(name = "service_duration")
    private String duration;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
    private Provider provider;
}
