package hcmute.fit.event_management.controller.employee;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.service.IEmployeeService;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.ISubtaskService;
import hcmute.fit.event_management.service.ITeamService;
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

    @Autowired
    private ITeamService teamService;
    @Autowired
    private ISubtaskService subtaskService;

    @GetMapping("/event/{empId}")
    public ResponseEntity<?> findEventId(@PathVariable int empId) {
        List<EventDTO> events = eventService.getAllEventByEmp(empId);
        Response response = new Response();
        response.setData(events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{employeeId}/team/{eventId}")
    public ResponseEntity<?> findTeamEventId(@PathVariable int employeeId, @PathVariable int eventId) {
        Response response = new Response();
        response.setData(teamService.memberTeamInEvent(eventId, employeeId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateEvent(@PathVariable int taskId, @RequestBody SubTaskDTO subTaskDTO) {
        Response response = new Response();
        response.setData(subtaskService.updateSubtask(taskId, subTaskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}/subtask/{taskId}")
    public ResponseEntity<?> findSubTaskEvent(@PathVariable int employeeId, @PathVariable int taskId) {
        Response response = new Response();
        response.setData(subtaskService.getListSubtaskByEmployeeId(employeeId, taskId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/list-event/{accountId}")
    public ResponseEntity<?> getAllEvent(@PathVariable Integer accountId){
        List<EventDTO> events = eventService.getAllEvents(accountId);
        Response response = new Response();
        response.setData(events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/profile/{empId}")
    public ResponseEntity<?> profile(@PathVariable int empId) {
        Employee man = employeeService.findById(empId).orElse(new Employee());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(man, employeeDTO);
        Response response = new Response();
        response.setData(employeeDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile/{empId}")
    public ResponseEntity<?> updateProfile(@PathVariable int empId, @RequestBody EmployeeDTO employeeDTO) {
        Boolean result = employeeService.updateProfile(empId, employeeDTO);
        Response response = new Response();
        response.setData(result);
        if (result){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
