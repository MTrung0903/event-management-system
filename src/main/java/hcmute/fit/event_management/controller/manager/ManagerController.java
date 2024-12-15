package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.service.IManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

@RestController
public class ManagerController {
    @Autowired
    IManagerService managerService;
    @GetMapping("man/profile/{manId}")
    public ResponseEntity<?> profile(@PathVariable int manId) {
        Manager man = managerService.findById(manId).orElse(new Manager());
        ManagerDTO managerDTO = new ManagerDTO();
        BeanUtils.copyProperties(man, managerDTO);
        Response response = new Response();
        response.setData(managerDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("man/profile/{manId}")
    public ResponseEntity<?> updateProfile(@PathVariable int manId, @RequestBody ManagerDTO managerDTO) {
        Boolean result = managerService.updateProfile(manId, managerDTO);
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
