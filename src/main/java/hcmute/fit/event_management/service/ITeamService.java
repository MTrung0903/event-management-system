package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.Team;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ITeamService {

    List<TeamDTO> getTeamsOfEvent(int eventId);

    TeamDTO getTeamById(int teamId);

    boolean addTeam(int eventId, String teamName);

    boolean updateTeam(int teamId, int eventId, String teamName);

    boolean deleteTeam(int teamId);
}
