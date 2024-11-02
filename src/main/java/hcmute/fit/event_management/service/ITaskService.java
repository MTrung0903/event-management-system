package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Task> findAll();

    List<Task> findAllById(Iterable<Integer> integers);

    <S extends Task> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Task entity);

    void deleteById(Integer integer);

    Optional<Task> findById(Integer integer);

    <S extends Task> S save(S entity);

    List<Task> findAll(Sort sort);

    <S extends Task> Optional<S> findOne(Example<S> example);
}
