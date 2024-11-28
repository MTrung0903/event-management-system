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

    boolean addSubtask(int taskId, SubTaskDTO subtaskDTO);

    boolean updateSubtask(int taskId, SubTaskDTO subtaskDTO);

    boolean deleteSubtask(int subtaskId);

    boolean actionSubtask(int employeeId, int subtaskId, String action);
}
