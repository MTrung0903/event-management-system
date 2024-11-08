package hcmute.fit.event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRole {
    @Id
    @Column(name = "account_id")
    private int accountID;
    @Id
    @Column(name = "role_id")
    private int roleID;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

}
