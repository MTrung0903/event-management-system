package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> findAll();

    List<Event> findAllById(Iterable<Integer> integers);

    <S extends Event> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteById(Integer integer);

    <S extends Event> S save(S entity);

    List<Event> findAll(Sort sort);

    <S extends Event> Optional<S> findOne(Example<S> example);
}
