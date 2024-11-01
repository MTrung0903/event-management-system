package hcmute.fit.event_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="provider")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider {
    @Id
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
}
