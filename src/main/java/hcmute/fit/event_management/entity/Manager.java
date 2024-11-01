package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    @Column(name = "man_id")
    private int manID;
    @Column(name = "full_name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "man_id")
    private Account account;
}
