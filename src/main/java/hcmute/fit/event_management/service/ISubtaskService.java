package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.entity.SubTask;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import payload.Response;

import java.util.List;
import java.util.Optional;

public interface ISubtaskService {

    List<SubTaskDTO> getAllSubtasksOfTask(int taskId);

    List<SubTaskDTO> listSubtaskFromEvent(int eventId);

    SubTaskDTO getSubtaskById(int subtaskId);

    Response addSubtask(int taskId, SubTaskDTO subtaskDTO);

    Response updateSubtask(int taskId, SubTaskDTO subtaskDTO);

    boolean deleteSubtask(int subtaskId);

    boolean actionSubtask(int employeeId, int subtaskId, String action);

    boolean assignedSubtask(int employeeId, int subtaskId);

    boolean assignedUpdate(int subtaskId, int employeeId);

    boolean changeStatus(int subtaskId, String status);

    boolean changeEmployeeAssigned(int subtaskId, int employeeId);
}
