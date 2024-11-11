package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SponsorShipDTO;
import hcmute.fit.event_management.entity.SponsorShip;
import hcmute.fit.event_management.service.ISponsorShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/sponsorship")
public class SponsorShipController {
    @Autowired
    private ISponsorShipService sponsorShipService;

    @GetMapping("")
    public ResponseEntity<?> getAllSponsorShips() {
        List<SponsorShipDTO> list = sponsorShipService.getAllSponsorShips();
        Response response  = new Response();
        response.setData(list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findSponsorById(@RequestParam int id){
        SponsorShipDTO shipDTO = sponsorShipService.getSponsorShipByID(id);
        Response response  = new Response();
        response.setData(shipDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSponsorShip(@RequestParam String level, @RequestParam String benefit){
        Response response = new Response();
        response.setData(sponsorShipService.addSponsorShip(level, benefit));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSponsorShip(@RequestParam int id,@RequestParam String level, @RequestParam String benefit){
        Response response = new Response();
        response.setData(sponsorShipService.updateSponsorShip(id,level,benefit));
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSponsorShip(@RequestParam int id){
        Response response = new Response();
        response.setData(sponsorShipService.deleteSponsorShip(id));
        return ResponseEntity.ok(response);
    }
}
