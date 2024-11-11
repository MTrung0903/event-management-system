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

    @GetMapping("")
    public ResponseEntity<?> getSubtaskOfTask(int taskId){
        List<SubTaskDTO> listSubtask = subtaskService.getAllSubtasksOfTask(taskId);
        Response response = new Response();
        response.setData(listSubtask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findSubtaskById(int subtaskId){
        SubTaskDTO subtask = subtaskService.getSubtaskById(subtaskId);
        Response response = new Response();
        response.setData(subtask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addSubtask(@RequestParam int taskId,
                                        @RequestParam int employeeId,
                                        @RequestParam String subtaskName,
                                        @RequestParam String subtaskDesc,
                                        @RequestParam String subtaskDl,
                                        @RequestParam String subtaskStatus){
        Response response = new Response();
        response.setData(subtaskService.addSubtask(taskId, employeeId, subtaskName,
                subtaskDesc, subtaskDl, subtaskStatus));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateSubtask(@RequestParam int subtaskId,
                                           @RequestParam int taskId,
                                           @RequestParam int employeeId,
                                           @RequestParam String subtaskName,
                                           @RequestParam String subtaskDesc,
                                           @RequestParam String subtaskDl,
                                           @RequestParam String subtaskStatus){
        Response response = new Response();
        response.setData(subtaskService.updateSubtask(subtaskId,taskId,employeeId,
                subtaskName,subtaskDesc,subtaskDl,subtaskStatus));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSubtask(@RequestParam int subtaskId){
        Response response = new Response();
        response.setData(subtaskService.deleteSubtask(subtaskId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
