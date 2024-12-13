package hcmute.fit.event_management.controller.employee;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.service.IEmployeeService;
import hcmute.fit.event_management.service.IEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class HomeEmployeeController {
    @Autowired
    IEventService eventService;
    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/event/{empId}")
    public ResponseEntity<?> findEventId(@PathVariable int empId) {
        List<EventDTO> events = eventService.getAllEventByEmp(empId);
        Response response = new Response();
        response.setData(events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/profile/{empId}")
    public ResponseEntity<?> profile(@PathVariable int empId) {
        Employee emp = employeeService.findById(empId).orElse(new Employee());
        Manager man = emp.getManager();
        ManagerDTO manDTO = new ManagerDTO();
        BeanUtils.copyProperties(man, manDTO);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(emp, employeeDTO);
        employeeDTO.setManager(manDTO);
        Response response = new Response();
        response.setData(employeeDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile/{empId}")
    public ResponseEntity<?> updateProfile(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) {
        Boolean result = employeeService.updateProfile( empId, employeeDTO);
        Response response = new Response();
        response.setData(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
