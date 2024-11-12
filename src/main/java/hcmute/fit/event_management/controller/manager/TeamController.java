package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.Team;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/team")
public class TeamController {
    @Autowired
    private ITeamService teamService;

    @GetMapping("")
    public ResponseEntity<?> getTeamsOfEvent(int eventId){
        List<TeamDTO> teams = teamService.getTeamsOfEvent(eventId);
        Response response  = new Response();
        response.setData(teams);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findTeamById(int teamId){
        TeamDTO team = teamService.getTeamById(teamId);
        Response response  = new Response();
        response.setData(team);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody TeamDTO team){
        Response response  = new Response();
        response.setData(teamService.addTeam(team));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateTeam(@RequestBody TeamDTO team){
        Response response  = new Response();
        response.setData(teamService.updateTeam(team));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
