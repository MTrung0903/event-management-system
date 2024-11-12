package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.dto.TaskDTO;
import hcmute.fit.event_management.entity.SubTask;
import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.TaskRepository;
import hcmute.fit.event_management.repository.TeamRepository;
import hcmute.fit.event_management.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private EventRepository eventRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getTasksOfEvent(int eventId) {
        List<Task> tasks = taskRepository.findByEventId(eventId);
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();

            BeanUtils.copyProperties(task, taskDTO);
            taskDTO.setEventId(eventId);
            List<SubTaskDTO> subTaskDTOS = new ArrayList<>();
            List<SubTask>  subTasks = task.getListSubTasks();
            for(SubTask subTask : subTasks){
                SubTaskDTO subTaskDTO = new SubTaskDTO();
                BeanUtils.copyProperties(subTask, subTaskDTO);
                subTaskDTO.setEmployeeId(subTask.getEmployee().getId());
                subTaskDTOS.add(subTaskDTO);
            }
            taskDTO.setListSubTasks(subTaskDTOS);
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }
    @Override
    public TaskDTO findTaskById(int subTaskId) {
        Optional<Task> task = taskRepository.findById(subTaskId);
        TaskDTO taskDTO = new TaskDTO();
        if(task.isPresent()){

            BeanUtils.copyProperties(task.get(), taskDTO);
            taskDTO.setEventId(task.get().getEvent().getEventID());
            List<SubTaskDTO> subTaskDTOS = new ArrayList<>();
            List<SubTask>  subTasks = task.get().getListSubTasks();
            for(SubTask subTask : subTasks){
                SubTaskDTO subTaskDTO = new SubTaskDTO();
                BeanUtils.copyProperties(subTask, subTaskDTO);
                subTaskDTO.setEmployeeId(subTask.getEmployee().getId());
                subTaskDTOS.add(subTaskDTO);
            }
            taskDTO.setListSubTasks(subTaskDTOS);
        }
        return taskDTO;
    }
    @Override
    public boolean addTask(TaskDTO taskDTO) {
        boolean isSuccess = false;
        try{
            if(eventRepository.findById(taskDTO.getEventId()).isPresent() && teamRepository.findById(taskDTO.getTeamId()).isPresent()){
                Task task = new Task();
                task.setTaskName(taskDTO.getTaskName());
                task.setTaskDesc(taskDTO.getTaskDesc());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(taskDTO.getTaskDl().trim());
                task.setTaskDl(date);
                task.setTaskStatus(taskDTO.getTaskStatus());
                task.setTeam(teamRepository.findById(taskDTO.getTeamId()).get());
                task.setEvent(eventRepository.findById(taskDTO.getEventId()).get());
                taskRepository.save(task);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateTask(TaskDTO taskDTO) {
        boolean isSuccess = false;
        try{
            if(taskRepository.findById(taskDTO.getTaskId()).isPresent()){
                if(eventRepository.findById(taskDTO.getEventId()).isPresent() && teamRepository.findById(taskDTO.getEventId()).isPresent()){
                    Task task = taskRepository.findById(taskDTO.getTaskId()).get();
                    task.setTaskName(taskDTO.getTaskName());
                    task.setTaskDesc(taskDTO.getTaskDesc());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = formatter.parse(taskDTO.getTaskDl().trim());
                    task.setTaskDl(date);
                    task.setTaskStatus(taskDTO.getTaskStatus());
                    task.setTeam(teamRepository.findById(taskDTO.getTeamId()).get());
                    task.setEvent(eventRepository.findById(taskDTO.getEventId()).get());
                    taskRepository.save(task);
                    isSuccess = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteTask(int taskId) {
        boolean isSuccess = false;
        try {
            if(taskRepository.findById(taskId).isPresent()){
                taskRepository.deleteById(taskId);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
}
