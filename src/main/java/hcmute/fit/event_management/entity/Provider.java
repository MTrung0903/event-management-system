package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name ="provider")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private int id;
    @Column(name = "provider_name")
    private String name;
    @Column(name = "contact_person")
    private String contact;
    @Column(name = "contact_email")
    private String email;
    @Column(name = "contact_phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private Set<ProviderEvent> listProviderEvents;

    @OneToMany(mappedBy = "provider")
    private Set<ProviderService> listProviderServices;
}