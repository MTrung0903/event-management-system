package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.SponsorEvent;
import hcmute.fit.event_management.entity.keys.SponsorEventId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISponsorEventService {
    List<SponsorEvent> findAll();

    long count();

    List<SponsorEvent> findAllById(Iterable<SponsorEventId> sponsorEventIds);

    <S extends SponsorEvent> Optional<S> findOne(Example<S> example);

    List<SponsorEvent> findAll(Sort sort);

    void deleteById(SponsorEventId sponsorEventId);

    <S extends SponsorEvent> List<S> findAll(Example<S> example, Sort sort);

    void delete(SponsorEvent entity);

    <S extends SponsorEvent> S save(S entity);

    <S extends SponsorEvent> List<S> findAll(Example<S> example);

    Optional<SponsorEvent> findById(SponsorEventId sponsorEventId);

    Page<SponsorEvent> findAll(Pageable pageable);

    <S extends SponsorEvent> long count(Example<S> example);

    boolean existsById(SponsorEventId sponsorEventId);

    <S extends SponsorEvent> Page<S> findAll(Example<S> example, Pageable pageable);
}