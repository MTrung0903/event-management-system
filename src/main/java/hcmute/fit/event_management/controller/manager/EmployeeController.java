package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.Response;

@RestController
@RequestMapping("man/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("")
    public ResponseEntity<?> findEmployeeAddTOTeam(){
        Response response  = new Response();
        response.setData(employeeService.getEmployeesNotJoinTeam());
        return ResponseEntity.ok(response);
    }
}
