package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Section;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISectionService {
    List<Section> findAll();

    List<Section> findAllById(Iterable<Integer> integers);

    <S extends Section> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteById(Integer integer);

    Optional<Section> findById(Integer integer);

    <S extends Section> S save(S entity);

    List<Section> findAll(Sort sort);

    <S extends Section> Optional<S> findOne(Example<S> example);
}
