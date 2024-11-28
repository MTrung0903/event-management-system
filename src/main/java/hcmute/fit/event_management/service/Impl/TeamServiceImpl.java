package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.TaskDTO;
import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.entity.Team;
import hcmute.fit.event_management.entity.TeamEmployee;
import hcmute.fit.event_management.entity.keys.TeamEmployeeId;
import hcmute.fit.event_management.repository.*;
import hcmute.fit.event_management.service.IEmployeeService;
import hcmute.fit.event_management.service.ITeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamEmployeeRepository teamEmployeeRepository;

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private TaskRepository taskRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDTO> getTeamsOfEvent(int eventId) {
        List<Team> teams = teamRepository.findByEventId(eventId);
        List<TeamDTO> teamDTOs = new ArrayList<>();

        for (Team team : teams) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setTeamId(team.getTeamId());
            teamDTO.setTeamName(team.getTeamName());
            List<Task> tasks = team.getListTasks();
            List<TaskDTO> taskDTOs = new ArrayList<>();
            for (Task task : tasks) {
                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setTaskId(task.getTaskId());
                taskDTO.setTaskName(task.getTaskName());
                taskDTO.setTaskStatus(task.getTaskStatus());
                taskDTO.setTaskDesc(task.getTaskDesc());
                taskDTO.setTaskDl(task.getTaskDl().toString());
                taskDTOs.add(taskDTO);
            }
            teamDTO.setListTasks(taskDTOs);
            teamDTOs.add(teamDTO);
        }
        return teamDTOs;
    }
    @Override
    public TeamDTO getDetailTeamById(int teamId) {
        Optional<Team> cpm = teamRepository.findById(teamId);
        TeamDTO teamDTO = new TeamDTO();
        if(cpm.isPresent()) {
            Team team = cpm.get();
            teamDTO.setTeamId(team.getTeamId());
            teamDTO.setTeamName(team.getTeamName());
            teamDTO.setEventId(team.getEvent().getEventID());
            //List<TeamEmployee> teamEmployees = teamEmployeeRepository.findByTeamId(team.getTeamId());
            List<EmployeeDTO> listEmployee = employeeService.getEmployeeByTeamId(team.getTeamId());
            teamDTO.setListEmployees(listEmployee);

        }
        return teamDTO;
    }
    @Override
    public List<TeamDTO> getAllTeamsByEventId(int eventId) {
        List<Team> teams = teamRepository.findByEventId(eventId);
        List<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setTeamId(team.getTeamId());
            teamDTO.setTeamName(team.getTeamName());
            teamDTO.setEventId(team.getEvent().getEventID());
            teamDTO.setListEmployees(employeeService.getEmployeeByTeamId(team.getTeamId()));
            teamDTOs.add(teamDTO);
        }

        return teamDTOs;
    }
    @Override
    public boolean addTeam(TeamDTO teamDTO){
        boolean isSuccess = false;
        try{
            Team team = new Team();
            team.setTeamName(teamDTO.getTeamName());
            team.setEvent(eventRepository.findById(teamDTO.getEventId()).get());
            teamRepository.save(team);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateTeam(TeamDTO teamDTO){
        boolean isSuccess = false;
        try{
           if(teamRepository.findById(teamDTO.getTeamId()).isPresent()) {
               Team team = teamRepository.findById(teamDTO.getTeamId()).get();
               team.setTeamName(teamDTO.getTeamName());
               team.setEvent(eventRepository.findById(teamDTO.getEventId()).get());
               teamRepository.save(team);
               isSuccess = true;
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteTeam(int teamId){
        boolean isSuccess = false;
        if(teamRepository.findById(teamId).isPresent()) {
            teamRepository.delete(teamRepository.findById(teamId).get());
            isSuccess = true;
        }
        return isSuccess;
    }
    @Override
    public boolean addMemberToTeam(int teamId, int employeeId){
        boolean isSuccess = false;
        try{
            if(teamRepository.findById(teamId).isPresent() && employeeRepository.findById(employeeId).isPresent()) {
                TeamEmployee teamEmployee = new TeamEmployee();
                TeamEmployeeId teamEmployeeId = new TeamEmployeeId(employeeId, teamId);
                teamEmployee.setId(teamEmployeeId);
                teamEmployee.setEmployee(employeeRepository.findById(employeeId).get());
                teamEmployee.setTeam(teamRepository.findById(teamId).get());
                teamEmployeeRepository.save(teamEmployee);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Add member to team failed" + e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteMemberFromTeam(int teamId, int employeeId){
        boolean isSuccess = false;
        try{
            if(teamRepository.findById(teamId).isPresent() && employeeRepository.findById(employeeId).isPresent()) {
                TeamEmployee teamEmployee = teamEmployeeRepository.findByTeamIdAndEmployee(teamId, employeeId);
                teamEmployeeRepository.delete(teamEmployee);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Delete member from team failed" + e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public List<TeamDTO> getListTeamToAssignedTask(int taskId, int eventId) {
        try {
            Task taskAssigned = taskRepository.findById(taskId).get();
            List<Team> teams = teamRepository.findByEventId(eventId);
            List<Team> assignedTeams = new ArrayList<>();

            for (Team team : teams) {
                List<Task> tasks = taskRepository.findByTeamId(team.getTeamId());
                boolean canAssign = true;

                for (Task task : tasks) {
                    // Kiểm tra nếu deadline nằm trong khoảng [startDate, endDate]
                    if (!task.getTaskDl().before(taskAssigned.getCreateDate()) && !task.getTaskDl().after(taskAssigned.getTaskDl())) {
                        canAssign = false;
                        break;
                    }
                }

                if (canAssign) {
                    assignedTeams.add(team);
                }
            }
            List<TeamDTO> list = new ArrayList<>();
            for (Team team : assignedTeams) {
                TeamDTO teamDTO = new TeamDTO();
                BeanUtils.copyProperties(team, teamDTO);
                teamDTO.setEventId(eventId);

                list.add(teamDTO);
            }
            return list;

        } catch (Exception e) {
            throw new RuntimeException("Error while assigning teams to task", e);
        }
    }
}
