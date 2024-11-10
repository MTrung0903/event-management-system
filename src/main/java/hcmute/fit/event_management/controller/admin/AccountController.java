package hcmute.fit.event_management.controller.admin;

import hcmute.fit.event_management.dto.AccountDTO;

import hcmute.fit.event_management.service.Impl.AccountRoleServiceImpl;
import hcmute.fit.event_management.service.Impl.AccountServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import payload.Response;


import java.util.List;


@RestController
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    AccountRoleServiceImpl accountRoleServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping()
    public ResponseEntity<?> getAccount() {
        List<AccountDTO> listAccountDTO = accountServiceImpl.getAllAccountDTOs();
        Response response = new Response(200, "Success", listAccountDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO) {
        Boolean status = accountServiceImpl.addOrUpdateAccount(false, accountDTO);
        Response response;
        if (status) {
            response = new Response(200, "Account created successfully", "");
        }
        else {
            response = new Response(200, "Account created unsuccessfully", "");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO) {
        Boolean status = accountServiceImpl.addOrUpdateAccount(true, accountDTO);
        Response response;
        if (status) {
            response = new Response(200, "Account updated successfully", "");
        }
        else {
            response = new Response(200, "Account updated unsuccessfully", "");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
