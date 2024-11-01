package hcmute.fit.event_management.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "account_id")
    private int accountID;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "account")
    private Employee employee;

    @OneToOne(mappedBy = "account")
    private Manager manager;
}
