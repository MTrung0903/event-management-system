package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.SponsorShip;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISponsorShipService {
    long count();

    void deleteAll();

    Optional<SponsorShip> findById(Integer integer);

    <S extends SponsorShip> S save(S entity);

    List<SponsorShip> findAll(Sort sort);

    <S extends SponsorShip> Optional<S> findOne(Example<S> example);

    void deleteById(Integer integer);
}
