package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SponsorDTO;
import hcmute.fit.event_management.dto.SponsorShipDTO;
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

    @GetMapping("/{sponsorId}")
    public ResponseEntity<?> findSponsorById(@PathVariable int sponsorId) {
        SponsorDTO sponsor = sponsorService.getSponsorById(sponsorId);
        Response response = new Response();
        response.setData(sponsor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addSponsor(@RequestBody SponsorDTO sponsorDTO) {
        Response response = new Response();
        response.setData(sponsorService.addSponsor(sponsorDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateSponsor(@RequestBody SponsorDTO sponsorDTO) {
        Response response = new Response();
        response.setData(sponsorService.updatSponsor(sponsorDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSponsor(@PathVariable int id) {
        Response response = new Response();
        response.setData(sponsorService.deleteSponsor(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
