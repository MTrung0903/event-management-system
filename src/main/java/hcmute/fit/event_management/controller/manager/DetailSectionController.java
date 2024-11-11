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
    @GetMapping("")
    public ResponseEntity<?> getDetailSectionOfEvent(@RequestParam int eventId){
        List<DetailSectionDTO> list = detailSectionService.getListDetailSectionsByEventId(eventId);
        Response response = new Response();
        response.setData(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<?> getDetailSectionById(@RequestParam int id){
        DetailSectionDTO detailSectionDTO = detailSectionService.getDetailSectionById(id);
        Response response = new Response();
        response.setData(detailSectionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addDetailSection(@RequestParam int speakerId,
                                              @RequestParam String speakerTitle,
                                              @RequestParam int eventId,
                                              @RequestParam int sectionId){
        Response response = new Response();
        response.setData(detailSectionService.addDetailSection(speakerId, speakerTitle, eventId, sectionId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDetailSection(@RequestParam int detailSectionId,
                                                 @RequestParam int speakerId,
                                                 @RequestParam String speakerTitle,
                                                 @RequestParam int eventId,
                                                 @RequestParam int sectionId){
        Response response = new Response();
        response.setData(detailSectionService.updateDetailSection(detailSectionId, speakerId, speakerTitle, eventId, sectionId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDetailSection(@RequestParam int detailSectionId){
        Response response = new Response();
        response.setData(detailSectionService.deleteDetailSection(detailSectionId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
