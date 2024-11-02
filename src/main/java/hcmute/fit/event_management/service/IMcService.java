package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Mc;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IMcService {
    List<Mc> findAll();

    List<Mc> findAllById(Iterable<Integer> integers);

    <S extends Mc> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteById(Integer integer);

    <S extends Mc> S save(S entity);

    Optional<Mc> findById(Integer integer);

    List<Mc> findAll(Sort sort);

    <S extends Mc> Optional<S> findOne(Example<S> example);
}
