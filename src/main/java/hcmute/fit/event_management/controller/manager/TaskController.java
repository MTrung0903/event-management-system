package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.TaskDTO;
import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.service.IEventService;
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

    @GetMapping("")
    public ResponseEntity<?> getTaskOfEvent(@RequestParam int eventId){
        List<TaskDTO> listTask = taskService.getTasksOfEvent(eventId);
        Response response = new Response();
        response.setData(listTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("find")
    public ResponseEntity<?> getTaskById(@RequestParam int taskId){
        TaskDTO task = taskService.findTaskById(taskId);
        Response response = new Response();
        response.setData(task);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("add")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO){
        Response response = new Response();
        response.setData(taskService.addTask(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO){
        Response response = new Response();
        response.setData(taskService.updateTask(taskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
