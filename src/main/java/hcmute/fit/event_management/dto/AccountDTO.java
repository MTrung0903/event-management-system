package hcmute.fit.event_management.dto;

import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int accountID;
    private String email;
    private String password;
    private boolean isActive;
    private Role role_id;
    private Employee employee_id;
    private Manager manager_id;
}
