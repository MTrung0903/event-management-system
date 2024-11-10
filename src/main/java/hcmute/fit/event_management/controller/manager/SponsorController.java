package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SponsorDTO;
import hcmute.fit.event_management.entity.Sponsor;
import hcmute.fit.event_management.service.ISponsorService;
import hcmute.fit.event_management.service.ISponsorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/sponsor")
public class SponsorController {
    @Autowired
    private  ISponsorService sponsorService;

    @GetMapping("")
    public ResponseEntity<?> getAllSponsors() {
        List<SponsorDTO> list = sponsorService.getAllSponsors();
        Response response = new Response();
        response.setData(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findSponsorById(@RequestParam int id) {
        SponsorDTO sponsor = sponsorService.getSponsorById(id);
        Response response = new Response();
        response.setData(sponsor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSponsor(@RequestParam String name,
                                           @RequestParam String contact,
                                           @RequestParam String email,
                                           @RequestParam String phone,
                                           @RequestParam String website,
                                           @RequestParam String address,
                                           @RequestParam int sponsorShipId) {
        Response response = new Response();
        response.setData(sponsorService.addSponsor(name, contact, email, phone, website, address, sponsorShipId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateSponsor(@RequestParam int id,
                                           @RequestParam String name,
                                           @RequestParam String contact,
                                           @RequestParam String email,
                                           @RequestParam String phone,
                                           @RequestParam String website,
                                           @RequestParam String address,
                                           @RequestParam int sponsorShipId){
        Response response = new Response();
        response.setData(sponsorService.updatSponsor(id, name, contact, email, phone, website, address, sponsorShipId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSponsor(@RequestParam int id) {
        Response response = new Response();
        response.setData(sponsorService.deleteSponsor(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
