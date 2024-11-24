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



    @GetMapping("/{teamId}")
    public ResponseEntity<?> findTeamById(@PathVariable int teamId){
        TeamDTO team = teamService.getTeamById(teamId);
        Response response  = new Response();
        response.setData(team);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addTeam(@RequestBody TeamDTO team){
        Response response  = new Response();
        response.setData(teamService.addTeam(team));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateTeam(@RequestBody TeamDTO team){
        Response response  = new Response();
        response.setData(teamService.updateTeam(team));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
