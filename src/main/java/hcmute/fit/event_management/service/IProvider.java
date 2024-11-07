package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Provider;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IProvider {
    List<Provider> findAll();

    List<Provider> findAllById(Iterable<Integer> integers);

    <S extends Provider> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteById(Integer integer);

    Optional<Provider> findById(Integer integer);

    <S extends Provider> S save(S entity);

    List<Provider> findAll(Sort sort);

    <S extends Provider> Optional<S> findOne(Example<S> example);
}
