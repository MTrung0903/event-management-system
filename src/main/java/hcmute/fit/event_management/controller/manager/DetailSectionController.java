package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.entity.DetailSection;
import hcmute.fit.event_management.service.IDetailSectionService;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.ISectionService;
import hcmute.fit.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/man/detailsection")
public class DetailSectionController {
    @Autowired
    private IDetailSectionService detailSectionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailSectionById(@PathVariable int id){
        DetailSectionDTO detailSectionDTO = detailSectionService.getDetailSectionById(id);
        Response response = new Response();
        response.setData(detailSectionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> addDetailSection(@RequestBody DetailSectionDTO detail){
        Response response = new Response();
        response.setData(detailSectionService.addDetailSection(detail));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateDetailSection(@RequestBody DetailSectionDTO detail){
        Response response = new Response();
        response.setData(detailSectionService.updateDetailSection(detail));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{detailSectionId}")
    public ResponseEntity<?> deleteDetailSection(@PathVariable int detailSectionId){
        Response response = new Response();
        response.setData(detailSectionService.deleteDetailSection(detailSectionId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
