package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EmployeeDTO;
import hcmute.fit.event_management.dto.TaskDTO;
import hcmute.fit.event_management.dto.TeamDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.entity.Team;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.TeamRepository;
import hcmute.fit.event_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EventRepository eventRepository;

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
                taskDTO.setTaskDl(task.getTaskDl());
                taskDTOs.add(taskDTO);
            }
            List<Employee> employees = team.getListEmployees();
            List<EmployeeDTO> employeeDTOs = new ArrayList<>();
            for (Employee employee : employees) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setFullName(employee.getFullName());
                employeeDTOs.add(employeeDTO);
            }
            teamDTO.setListEmployees(employeeDTOs);
            teamDTO.setListTasks(taskDTOs);
            teamDTOs.add(teamDTO);
        }
        return teamDTOs;
    }
    @Override
    public TeamDTO getTeamById(int teamId) {
        Optional<Team> cpm = teamRepository.findById(teamId);
        TeamDTO teamDTO = new TeamDTO();
        if(cpm.isPresent()) {
            Team team = cpm.get();

            teamDTO.setTeamId(team.getTeamId());
            teamDTO.setTeamName(team.getTeamName());
            List<Task> tasks = team.getListTasks();
            List<TaskDTO> taskDTOs = new ArrayList<>();
            for (Task task : tasks) {
                TaskDTO taskDTO = new TaskDTO();
                taskDTO.setTaskId(task.getTaskId());
                taskDTO.setTaskName(task.getTaskName());
                taskDTO.setTaskStatus(task.getTaskStatus());
                taskDTOs.add(taskDTO);
            }
            List<Employee> employees = team.getListEmployees();
            List<EmployeeDTO> employeeDTOs = new ArrayList<>();
            for (Employee employee : employees) {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setFullName(employee.getFullName());
                employeeDTOs.add(employeeDTO);
            }
            teamDTO.setListEmployees(employeeDTOs);
            teamDTO.setListTasks(taskDTOs);

        }
        return teamDTO;
    }
    @Override
    public boolean addTeam(int eventId, String teamName){
        boolean isSuccess = false;
        try{
            Team team = new Team();
            team.setTeamName(teamName);
            team.setEvent(eventRepository.findById(eventId).get());
            teamRepository.save(team);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateTeam(int teamId, int eventId, String teamName){
        boolean isSuccess = false;
        try{
           if(teamRepository.findById(teamId).isPresent()) {
               Team team = teamRepository.findById(teamId).get();
               team.setTeamName(teamName);
               team.setEvent(eventRepository.findById(eventId).get());
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
}
