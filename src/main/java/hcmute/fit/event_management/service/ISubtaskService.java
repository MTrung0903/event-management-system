package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.entity.SubTask;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISubtaskService {

    List<SubTaskDTO> getAllSubtasksOfTask(int taskId);

    SubTaskDTO getSubtaskById(int subtaskId);

    boolean addSubtask(int taskId, int employeeId, String subtaskName,
                       String subtaskDesc, String subtaskDl, String subtaskStatus);

    boolean updateSubtask(int subtaskId, int taskId, int employeeId, String subtaskName,
                          String subtaskDesc, String subtaskDl, String subtaskStatus);

    boolean deleteSubtask(int subtaskId);
}
