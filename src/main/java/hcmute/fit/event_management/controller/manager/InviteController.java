package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.InviteDTO;
import hcmute.fit.event_management.service.IInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/man/invite")
public class InviteController {
    @Autowired
    private IInviteService inviteService;

    @GetMapping("")
    public ResponseEntity<?> getListInviteByEventId(@RequestParam int eventId) {
        List<InviteDTO> listInvites= inviteService.getListInvitesByEventId(eventId);
        Response response = new Response();
        response.setData(listInvites);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findInviteById(@RequestParam int inviteId) {
        InviteDTO invite = inviteService.findInvite(inviteId);
        Response response = new Response();
        response.setData(invite);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInvite(@RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam String inviteDate,
                                       @RequestParam String status,
                                       @RequestParam int eventId) {
        Response response = new Response();
        response.setData(inviteService.addInvite(name, email, inviteDate, status,eventId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInvite(@RequestParam int inviteId,
                                          @RequestParam String name,
                                          @RequestParam String email,
                                          @RequestParam String inviteDate,
                                          @RequestParam String status) {
        Response response = new Response();
        response.setData(inviteService.updateInvite(inviteId,name, email, inviteDate, status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteInvite(@RequestParam int inviteId) {
        Response response = new Response();
        response.setData(inviteService.deleteInvite(inviteId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
