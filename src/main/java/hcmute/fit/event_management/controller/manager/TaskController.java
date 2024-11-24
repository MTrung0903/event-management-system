package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.dto.TaskDTO;
import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.ISubtaskService;
import hcmute.fit.event_management.service.ITaskService;
import hcmute.fit.event_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/task")
public class TaskController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ISubtaskService subtaskService;

    @GetMapping("")
    public ResponseEntity<?> getTaskOfEvent(@RequestParam int eventId){
        List<TaskDTO> listTask = taskService.getTasksOfEvent(eventId);
        Response response = new Response();
        response.setData(listTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable int taskId){
        TaskDTO task = taskService.findTaskById(taskId);
        Response response = new Response();
        response.setData(task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO){
        Response response = new Response();
        response.setData(taskService.addTask(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO){
        Response response = new Response();
        response.setData(taskService.updateTask(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{taskId}/subtask")
    public ResponseEntity<?> getSubtaskOfTask(int taskId){
        List<SubTaskDTO> listSubtask = subtaskService.getAllSubtasksOfTask(taskId);
        Response response = new Response();
        response.setData(listSubtask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
