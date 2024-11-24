package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.McDTO;
import hcmute.fit.event_management.entity.Mc;
import hcmute.fit.event_management.service.IMcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;


@RestController
@RequestMapping("man/mc")
public class McController {
    @Autowired
    private IMcService mcService;

    @GetMapping("")
    public ResponseEntity<?> getAllMC() {
        List<McDTO> list = mcService.getListMc();
        Response response = new Response();
        response.setData(list);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{mcId}")
    public ResponseEntity<?> findMC(@PathVariable int mcId) {
        McDTO mc = mcService.findMcById(mcId);
        Response response = new Response();
        response.setData(mc);
        return ResponseEntity.ok(response);
    }
    @PostMapping("")
    public ResponseEntity<?> addMc(@RequestBody McDTO mcDto){
        Response response = new Response();
        response.setData(mcService.addMc(mcDto));
        return ResponseEntity.ok(response);
    }

    @PutMapping("")
    public ResponseEntity<?> updateMc(@RequestBody McDTO mcDto){
        Response response = new Response();
        response.setData(mcService.updateMc(mcDto));
        return ResponseEntity.ok(response);
    }

}
