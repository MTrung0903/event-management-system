package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.IFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("man/event")
public class EventController {
    @Autowired
    private IEventService eventService;

    @Autowired
    private IFileService fileService;

    @GetMapping("/files/{filesname:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filesname){
        Resource resource = fileService.load(filesname);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filesname + "\"")
                .body(resource);
    }
    @PostMapping("uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        Response response = new Response();
        response.setData(fileService.saveFiles(file));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("add")
    public ResponseEntity<?> addEvent(@RequestParam("image") MultipartFile image,
                                      @ModelAttribute EventDTO event){
        Response response = new Response();
        response.setData(eventService.addEvent(image,event));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateEvent(@RequestParam("image") MultipartFile image,  @RequestBody EventDTO event){
        Response response = new Response();
        response.setData(eventService.updateEvent(image,event));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllEvent(){
        List<EventDTO> events = eventService.getAllEvents();
        Response response = new Response();
        response.setData(events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<?> getEventById(@RequestParam("id") int eventId){
        EventDTO event = eventService.getEventById(eventId);
        Response response = new Response();
        response.setData(event);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
