package hcmute.fit.event_management.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class Home {
    @GetMapping("test")
    public String home() {
        return "Hello World";
    }
}
