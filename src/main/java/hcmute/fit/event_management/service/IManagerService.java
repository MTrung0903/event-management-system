package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Manager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    List<Manager> findAll();

    List<Manager> findAllById(Iterable<Integer> integers);

    <S extends Manager> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllById(Iterable<? extends Integer> integers);

    <S extends Manager> S save(S entity);

    Optional<Manager> findById(Integer integer);

    void deleteById(Integer integer);

    <S extends Manager> Optional<S> findOne(Example<S> example);

    List<Manager> findAll(Sort sort);
}
