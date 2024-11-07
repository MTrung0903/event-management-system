package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Attendee;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IAttendeeService {
    List<Attendee> findAll();

    List<Attendee> findAllById(Iterable<Integer> integers);

    long count();

    void delete(Attendee entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteById(Integer integer);

    Optional<Attendee> findById(Integer integer);

    <S extends Attendee> S save(S entity);

    List<Attendee> findAll(Sort sort);

    <S extends Attendee> Optional<S> findOne(Example<S> example);
}
