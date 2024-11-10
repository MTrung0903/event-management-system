package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SpeakerDTO;
import hcmute.fit.event_management.entity.Speaker;
import hcmute.fit.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("man/speaker")
public class SpeakerController {
    @Autowired
    private ISpeakerService speakerService;

    @GetMapping("")
    public ResponseEntity<?> getAllSpeakers() {
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers();
        Response response = new Response();
        response.setData(speakers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> getSpeakerById(@RequestParam int speakerId) {
        SpeakerDTO speaker = speakerService.getSpeakerById(speakerId);
        Response response = new Response();
        response.setData(speaker);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSpeaker(@RequestParam String name,
                                        @RequestParam String email,
                                        @RequestParam String title,
                                        @RequestParam String phone,
                                        @RequestParam String address,
                                        @RequestParam String description) {
        Response response = new Response();
        response.setData(speakerService.addSpeaker(name, email, title, phone, address, description));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateSpeaker(@RequestParam int id,
                                           @RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam String title,
                                           @RequestParam String phone,
                                           @RequestParam String address,
                                           @RequestParam String description){
        Response response = new Response();
        response.setData(speakerService.updateSpeaker(id, name, email, title, phone, address, description));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSpeaker(@RequestParam int id) {
        Response response = new Response();
        response.setData(speakerService.deleteSpeaker(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
