package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.SubTaskDTO;
import hcmute.fit.event_management.entity.SubTask;
import hcmute.fit.event_management.repository.EmployeeRepository;
import hcmute.fit.event_management.repository.SubtaskRepository;
import hcmute.fit.event_management.repository.TaskRepository;
import hcmute.fit.event_management.service.ISubtaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubtaskServiceImpl implements ISubtaskService {
    @Autowired
    private SubtaskRepository subtaskRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public List<SubTaskDTO> getAllSubtasksOfTask(int taskId) {
        List<SubTask> subtasks = subtaskRepository.findByTaskId(taskId);
        List<SubTaskDTO> subtaskDTOs = new ArrayList<>();
        for (SubTask subtask : subtasks) {
            SubTaskDTO subtaskDTO = new SubTaskDTO();
            BeanUtils.copyProperties(subtask, subtaskDTO);
            subtaskDTO.setEmployeeId(subtask.getEmployee().getId());
            subtaskDTOs.add(subtaskDTO);
        }
        return subtaskDTOs;
    }
    @Override
    public SubTaskDTO getSubtaskById(int subtaskId) {
        Optional<SubTask> subtask = subtaskRepository.findById(subtaskId);
        SubTaskDTO subtaskDTO = new SubTaskDTO();
        if(subtask.isPresent()) {
            BeanUtils.copyProperties(subtask.get(), subtaskDTO);
            subtaskDTO.setEmployeeId(subtask.get().getEmployee().getId());
        }
        return subtaskDTO;
    }
    @Override
    public boolean addSubtask(int taskId, int employeeId, String subtaskName,
                              String subtaskDesc, String subtaskDl, String subtaskStatus) {
        boolean isSuccess = false;
        try {
            if(taskRepository.findById(taskId).isPresent() && employeeRepository.findById(employeeId).isPresent()) {
                SubTask subtask = new SubTask();
                subtask.setSubTaskName(subtaskName);
                subtask.setSubTaskDesc(subtaskDesc);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(subtaskDl.trim());
                subtask.setSubTaskDeadline(date);
                subtask.setStatus(subtaskStatus);
                subtask.setEmployee(employeeRepository.findById(employeeId).get());
                subtask.setTask(taskRepository.findById(taskId).get());
                subtaskRepository.save(subtask);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean updateSubtask(int subtaskId, int taskId, int employeeId, String subtaskName,
                                 String subtaskDesc, String subtaskDl, String subtaskStatus) {
        boolean isSuccess = false;
        try {
            if(subtaskRepository.findById(subtaskId).isPresent()) {
                if(taskRepository.findById(taskId).isPresent() && employeeRepository.findById(employeeId).isPresent()) {
                    SubTask subtask = subtaskRepository.findById(subtaskId).get();
                    subtask.setSubTaskName(subtaskName);
                    subtask.setSubTaskDesc(subtaskDesc);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = formatter.parse(subtaskDl.trim());
                    subtask.setSubTaskDeadline(date);
                    subtask.setStatus(subtaskStatus);
                    subtask.setEmployee(employeeRepository.findById(employeeId).get());
                    subtask.setTask(taskRepository.findById(taskId).get());
                    subtaskRepository.save(subtask);
                    isSuccess = true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteSubtask(int subtaskId) {
        boolean isSuccess = false;
        try {
            if(subtaskRepository.findById(subtaskId).isPresent()) {
                subtaskRepository.deleteById(subtaskId);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
}