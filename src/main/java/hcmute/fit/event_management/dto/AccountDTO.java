package hcmute.fit.event_management.dto;

import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
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
    private String role;
    private boolean isActive;
    private EmployeeDTO employee;
    private ManagerDTO manager;
}
