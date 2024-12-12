package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.*;
import hcmute.fit.event_management.repository.*;
import hcmute.fit.event_management.service.IEmployeeService;
import hcmute.fit.event_management.service.ITeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamEmployeeRepository teamEmployeeRepository;
    @Autowired
    private SubtaskRepository subtaskRepository;



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
            employeeDTO.setTeamId(teamId);

            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }


    @Override
    public List<EmployeeDTO> getEmployeesJoinTeam(int manId,int eventId) {

        List<Employee> list = employeeRepository.getListMemTOTeam(manId,eventId);


        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : list) {

                EmployeeDTO employeeDTO = new EmployeeDTO();
                BeanUtils.copyProperties(employee, employeeDTO);
                employeeDTOs.add(employeeDTO);

        }
        return employeeDTOs;
    }


    @Override
    public List<EmployeeDTO> getEmployeeToAssignedSubTask(){
        List<Employee> list = employeeRepository.findAll();
        List<SubTask>  subTaskList =  subtaskRepository.findAll();
        Set<Integer> listEmployeeIds = new HashSet<>();
        for(SubTask subTask : subTaskList) {
            listEmployeeIds.add(subTask.getEmployee().getId());
        }
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for(Employee employee : list) {
            if(!listEmployeeIds.contains(employee.getId())) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                BeanUtils.copyProperties(employee, employeeDTO);
                employeeDTOs.add(employeeDTO);
            }
        }
        return employeeDTOs;
    }
    @Override
    public List<EmployeeDTO> findEligibleEmployees(int teamId) {
        List<Employee> list = employeeRepository.findEmployeesWithoutSubtasksInTeam(teamId);
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : list) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            BeanUtils.copyProperties(employee, employeeDTO);
            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }




}
