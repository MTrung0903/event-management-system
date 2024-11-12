package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SectionDTO;
import hcmute.fit.event_management.entity.Section;
import hcmute.fit.event_management.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/section")
public class SectionController {

    @Autowired
    private ISectionService sectionService;

    @GetMapping("")
    public ResponseEntity<?> getSectionOfEvent(@RequestParam int eventId) {
        List<SectionDTO> listSection = sectionService.getSectionOfEvent(eventId);
        Response response = new Response();
        response.setData(listSection);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findSectionById(@RequestParam int sectionId) {
        SectionDTO section = sectionService.getSectionById(sectionId);
        Response response = new Response();
        response.setData(section);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addSection(@RequestBody SectionDTO sectionDTO) {
        Response response = new Response();
        response.setData(sectionService.addSection(sectionDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateSection(@RequestBody SectionDTO sectionDTO){
        Response response = new Response();
        response.setData(sectionService.updateSection(sectionDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSection(@RequestParam int sectionId){
        Response response = new Response();
        response.setData(sectionService.deleteSection(sectionId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
