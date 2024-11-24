package hcmute.fit.event_management.controller.manager;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.entity.SubTask;
import hcmute.fit.event_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import java.util.List;

@RestController
@RequestMapping("/man/subtask")
public class SubtaskController {
    @Autowired
    private ISubtaskService subtaskService;



    @GetMapping("/{subtaskId}")
    public ResponseEntity<?> findSubtaskById(@PathVariable int subtaskId){
        SubTaskDTO subtask = subtaskService.getSubtaskById(subtaskId);
        Response response = new Response();
        response.setData(subtask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/{taskId}")
    public ResponseEntity<?> addSubtask(@PathVariable int taskId,
                                        @RequestBody SubTaskDTO subtaskDTO){
        Response response = new Response();
        response.setData(subtaskService.addSubtask(taskId,subtaskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateSubtask(@PathVariable int taskId,
                                           @RequestBody SubTaskDTO subtaskDTO){
        Response response = new Response();
        response.setData(subtaskService.updateSubtask(taskId,subtaskDTO));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<?> deleteSubtask(@PathVariable int subtaskId){
        Response response = new Response();
        response.setData(subtaskService.deleteSubtask(subtaskId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
