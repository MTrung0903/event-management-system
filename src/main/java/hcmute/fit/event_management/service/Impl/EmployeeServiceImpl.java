package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.repository.EmployeeRepository;
import hcmute.fit.event_management.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



}
