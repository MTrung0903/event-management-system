package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.SubTask;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISubtaskService {
    List<SubTask> findAll();

    List<SubTask> findAllById(Iterable<Integer> integers);

    <S extends SubTask> List<S> saveAll(Iterable<S> entities);

    void deleteById(Integer integer);

    Optional<SubTask> findById(Integer integer);

    <S extends SubTask> S save(S entity);

    List<SubTask> findAll(Sort sort);

    <S extends SubTask> Optional<S> findOne(Example<S> example);
}
