package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.IManagerService;
import hcmute.fit.event_management.service.IMcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("man/event")
public class EventController {
    @Autowired
    private IEventService eventService;

}
