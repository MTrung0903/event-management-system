package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.entity.Employee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {


    List<EmployeeDTO> getEmployeeByTeamId(int teamId);


    Boolean updateProfile(int empId, EmployeeDTO employeeDTO);

    Optional<Employee> findById(Integer integer);

    List<EmployeeDTO> getEmployeesJoinTeam(int manId, int eventId);

    List<EmployeeDTO> getEmployeeToAssignedSubTask();

    List<EmployeeDTO> findEligibleEmployees(int eventId);


}
