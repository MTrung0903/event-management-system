package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Sponsor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISponsorService {
    List<Sponsor> findAll();

    List<Sponsor> findAllById(Iterable<Integer> integers);

    <S extends Sponsor> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Sponsor entity);

    void deleteById(Integer integer);

    Optional<Sponsor> findById(Integer integer);

    <S extends Sponsor> S save(S entity);

    List<Sponsor> findAll(Sort sort);

    <S extends Sponsor> Optional<S> findOne(Example<S> example);
}
