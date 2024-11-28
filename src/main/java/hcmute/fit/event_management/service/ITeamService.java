package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.Team;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITeamService {

    List<TeamDTO> getTeamsOfEvent(int eventId);

    TeamDTO getDetailTeamById(int teamId);

    List<TeamDTO> getAllTeamsByEventId(int eventId);

    boolean addTeam(TeamDTO teamDTO);

    boolean updateTeam(TeamDTO teamDTO);

    boolean deleteTeam(int teamId);

    boolean addMemberToTeam(int teamId, int employeeId);

    boolean deleteMemberFromTeam(int teamId, int employeeId);

    List<TeamDTO> getListTeamToAssignedTask(int taskId, int eventId);
}
