package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.TeamEmployee;
import hcmute.fit.event_management.repository.EmployeeRepository;
import hcmute.fit.event_management.repository.TeamEmployeeRepository;
import hcmute.fit.event_management.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamEmployeeRepository teamEmployeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeDTO> getEmployeeByTeamId(int teamId) {
        List<TeamEmployee> teamEmployees = teamEmployeeRepository.findByTeamId(teamId);
        List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
        for (TeamEmployee teamEmployee : teamEmployees) {
            Employee employee = employeeRepository.findById(teamEmployee.getEmployee().getId()).get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    @Override
    public List<EmployeeDTO> getEmployeesNotJoinTeam() {
        Set<Integer> listEmployeeIds = new HashSet<>();
        List<TeamEmployee> list = teamEmployeeRepository.findAll();
        for (TeamEmployee teamEmployee : list) {
            listEmployeeIds.add(teamEmployee.getEmployee().getId());
        }
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            if(!listEmployeeIds.contains(employee.getId())) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                BeanUtils.copyProperties(employee, employeeDTO);
                employeeDTOs.add(employeeDTO);
            }
        }
        return employeeDTOs;
    }

}
