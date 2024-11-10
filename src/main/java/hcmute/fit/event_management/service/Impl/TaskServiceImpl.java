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
//            taskDTO.setTaskId(task.getTaskId());
//            taskDTO.setTaskName(task.getTaskName());
//            taskDTO.setTaskDesc(task.getTaskDesc());
//            taskDTO.setTaskStatus(task.getTaskStatus());
//            taskDTO.setTaskDl(task.getTaskDl());
            BeanUtils.copyProperties(task, taskDTO);
            taskDTO.setEventId(eventId);
            List<SubTaskDTO> subTaskDTOS = new ArrayList<>();
            List<SubTask>  subTasks = task.getListSubTasks();
            for(SubTask subTask : subTasks){
                SubTaskDTO subTaskDTO = new SubTaskDTO();
//                subTaskDTO.setSubTaskId(subTask.getSubTaskId());
//                subTaskDTO.setSubTaskName(subTask.getSubTaskName());
//                subTaskDTO.setSubTaskDesc(subTask.getSubTaskDesc());
//                subTaskDTO.setStatus(subTask.getStatus());
//                subTaskDTO.setSubTaskDeadline(subTask.getSubTaskDeadline());
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
    public boolean addTask(int eventId, int teamId, String taskName, String taskDesc,
                           String taskDl, String taskStatus) {
        boolean isSuccess = false;
        try{
            if(eventRepository.findById(eventId).isPresent() && teamRepository.findById(teamId).isPresent()){
                Task task = new Task();
                task.setTaskName(taskName);
                task.setTaskDesc(taskDesc);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(taskDl.trim());
                task.setTaskDl(date);
                task.setTaskStatus(taskStatus);
                task.setTeam(teamRepository.findById(teamId).get());
                task.setEvent(eventRepository.findById(eventId).get());
                taskRepository.save(task);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateTask(int taskId, int eventId, int teamId, String taskName, String taskDesc,
                              String taskDl, String taskStatus) {
        boolean isSuccess = false;
        try{
            if(taskRepository.findById(taskId).isPresent()){
                if(eventRepository.findById(eventId).isPresent() && teamRepository.findById(teamId).isPresent()){
                    Task task = taskRepository.findById(taskId).get();
                    task.setTaskName(taskName);
                    task.setTaskDesc(taskDesc);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = formatter.parse(taskDl.trim());
                    task.setTaskDl(date);
                    task.setTaskStatus(taskStatus);
                    task.setTeam(teamRepository.findById(teamId).get());
                    task.setEvent(eventRepository.findById(eventId).get());
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
