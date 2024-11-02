package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Speaker;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISpeakerService {
    List<Speaker> findAll();

    List<Speaker> findAllById(Iterable<Integer> integers);

    <S extends Speaker> List<S> saveAll(Iterable<S> entities);

    void delete(Speaker entity);

    long count();

    void deleteById(Integer integer);

    <S extends Speaker> S save(S entity);

    Optional<Speaker> findById(Integer integer);

    List<Speaker> findAll(Sort sort);

    <S extends Speaker> Optional<S> findOne(Example<S> example);
}
