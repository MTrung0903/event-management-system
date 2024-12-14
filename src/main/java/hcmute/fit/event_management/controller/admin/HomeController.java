package hcmute.fit.event_management.controller.admin;

import hcmute.fit.event_management.dto.AccountDTO;
import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.dto.OverviewDTO;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    IAccountService accountService;
    @Autowired
    IAdminService adminService;
    @Autowired
    IManagerService managerService;

    @GetMapping("/events/overview")
    public ResponseEntity<?> overview() {
        OverviewDTO overviewDTO = adminService.getOverview();
        Response response= new Response(200, "Succesfully", overviewDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/provider/status")
    public String provider() {
        return "Hello World";
    }
    @GetMapping("/users")
    public ResponseEntity<?> users() {
        List<AccountDTO> accountDTO  = accountService.getAllAccountDTOs();
        Response response= new Response(200, "Succesfully", accountDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/managers")
    public ResponseEntity<?> manager() {
        List<ManagerDTO> managerDTOS  = managerService.findAllManager();
        Response response= new Response(200, "Succesfully", managerDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
