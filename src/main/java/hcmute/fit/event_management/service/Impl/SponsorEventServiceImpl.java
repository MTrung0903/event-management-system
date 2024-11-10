package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.entity.SponsorEvent;
import hcmute.fit.event_management.entity.keys.SponsorEventId;
import hcmute.fit.event_management.repository.SponsorEventRepository;
import hcmute.fit.event_management.service.ISponsorEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorEventServiceImpl implements ISponsorEventService {
    @Autowired
    SponsorEventRepository sponsorEventRepository;

    @Override
    public List<SponsorEvent> findAll() {
        return sponsorEventRepository.findAll();
    }

    @Override
    public long count() {
        return sponsorEventRepository.count();
    }

    @Override
    public List<SponsorEvent> findAllById(Iterable<SponsorEventId> sponsorEventIds) {
        return sponsorEventRepository.findAllById(sponsorEventIds);
    }

    @Override
    public <S extends SponsorEvent> Optional<S> findOne(Example<S> example) {
        return sponsorEventRepository.findOne(example);
    }

    @Override
    public List<SponsorEvent> findAll(Sort sort) {
        return sponsorEventRepository.findAll(sort);
    }

    @Override
    public void deleteById(SponsorEventId sponsorEventId) {
        sponsorEventRepository.deleteById(sponsorEventId);
    }

    @Override
    public <S extends SponsorEvent> List<S> findAll(Example<S> example, Sort sort) {
        return sponsorEventRepository.findAll(example, sort);
    }

    @Override
    public void delete(SponsorEvent entity) {
        sponsorEventRepository.delete(entity);
    }

    @Override
    public <S extends SponsorEvent> S save(S entity) {
        return sponsorEventRepository.save(entity);
    }

    @Override
    public <S extends SponsorEvent> List<S> findAll(Example<S> example) {
        return sponsorEventRepository.findAll(example);
    }

    @Override
    public Optional<SponsorEvent> findById(SponsorEventId sponsorEventId) {
        return sponsorEventRepository.findById(sponsorEventId);
    }

    @Override
    public Page<SponsorEvent> findAll(Pageable pageable) {
        return sponsorEventRepository.findAll(pageable);
    }

    @Override
    public <S extends SponsorEvent> long count(Example<S> example) {
        return sponsorEventRepository.count(example);
    }

    @Override
    public boolean existsById(SponsorEventId sponsorEventId) {
        return sponsorEventRepository.existsById(sponsorEventId);
    }

    @Override
    public <S extends SponsorEvent> Page<S> findAll(Example<S> example, Pageable pageable) {
        return sponsorEventRepository.findAll(example, pageable);
    }

}
