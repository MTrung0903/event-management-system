package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Task;
import hcmute.fit.event_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllById(Iterable<Integer> integers) {
        return taskRepository.findAllById(integers);
    }

    @Override
    public <S extends Task> List<S> saveAll(Iterable<S> entities) {
        return taskRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return taskRepository.count();
    }

    @Override
    public void delete(Task entity) {
        taskRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        taskRepository.deleteById(integer);
    }

    @Override
    public Optional<Task> findById(Integer integer) {
        return taskRepository.findById(integer);
    }

    @Override
    public <S extends Task> S save(S entity) {
        return taskRepository.save(entity);
    }

    @Override
    public List<Task> findAll(Sort sort) {
        return taskRepository.findAll(sort);
    }

    @Override
    public <S extends Task> Optional<S> findOne(Example<S> example) {
        return taskRepository.findOne(example);
    }
}
