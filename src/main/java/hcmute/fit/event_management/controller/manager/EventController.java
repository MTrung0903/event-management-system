package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.*;
import hcmute.fit.event_management.repository.ProviderRepository;
import hcmute.fit.event_management.service.*;

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
    private IInviteService inviteService;

    @Autowired
    private IDetailSectionService detailSectionService;

    @Autowired
    private IAttendeeService attendeeService;

    @Autowired
    private IProvider providerImpl;

    @Autowired
    private ISponsorService sponsorService;

    @Autowired
    private IEventService eventService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ISectionService sectionService;

    @Autowired
    private ITeamService teamService;

    @GetMapping("/files/{filesname:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filesname){
        Resource resource = fileService.load(filesname);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filesname + "\"")
                .body(resource);
    }

    @PostMapping("")
    public ResponseEntity<?> addEvent(@RequestParam("image") MultipartFile image,
                                      @ModelAttribute EventDTO event){
        System.out.println("EventDTO received: " + event);
        Response response = new Response();
        response.setData(eventService.addEvent(image,event));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateEvent(@RequestParam("image") MultipartFile image,
                                         @ModelAttribute EventDTO event){
        Response response = new Response();
        response.setData(eventService.updateEvent(image,event));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{eventId}/mc/{mcId}")
    public ResponseEntity<?> addMcEvent(@PathVariable Integer eventId, @PathVariable Integer mcId){
        Response response = new Response();
        response.setData(eventService.addMc(eventId,mcId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllEvent(){
        List<EventDTO> events = eventService.getAllEvents();
        Response response = new Response();
        response.setData(events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable("eventId") int eventId){
        EventDTO event = eventService.getEventById(eventId);
        Response response = new Response();
        response.setData(event);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/{eventId}/providers/{providerId}")
    public ResponseEntity<?> addProviderToEvent(
            @PathVariable int eventId,
            @PathVariable int providerId) {
        Response response = new Response();
        response.setData(providerImpl.addProviderForEvent(eventId,providerId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/{eventId}/sponsors/{sponsorId}")
    public ResponseEntity<?> addSponsorForEvent(@PathVariable int eventId,@PathVariable int sponsorId) {
        Response response = new Response();
        response.setData(sponsorService.addSponsorForEvent(sponsorId,eventId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}/attendee")
    public ResponseEntity<?> getListAttendees(@PathVariable("eventId") int eventId) {
        List<AttendeeDTO> listAttendee = attendeeService.gettListAttendeeByEventId(eventId);
        Response response = new Response();
        response.setData(listAttendee);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}/detail-section")
    public ResponseEntity<?> getDetailSectionOfEvent(@PathVariable int eventId){
        List<DetailSectionDTO> list = detailSectionService.getListDetailSectionsByEventId(eventId);
        Response response = new Response();
        response.setData(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}/list-invite")
    public ResponseEntity<?> getListInviteByEventId(@PathVariable int eventId) {
        List<InviteDTO> listInvites= inviteService.getListInvitesByEventId(eventId);
        Response response = new Response();
        response.setData(listInvites);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}/section")
    public ResponseEntity<?> getSectionOfEvent(@PathVariable int eventId) {
        List<SectionDTO> listSection = sectionService.getSectionOfEvent(eventId);
        Response response = new Response();
        response.setData(listSection);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{eventId}/team")
    public ResponseEntity<?> getTeamsOfEvent(@PathVariable  int eventId){
        List<TeamDTO> teams = teamService.getTeamsOfEvent(eventId);
        Response response  = new Response();
        response.setData(teams);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
