package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.entity.Speaker;
import hcmute.fit.event_management.repository.SpeakerRepository;
import hcmute.fit.event_management.service.ISpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements ISpeakerService {
    @Autowired
    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public List<Speaker> findAllById(Iterable<Integer> integers) {
        return speakerRepository.findAllById(integers);
    }

    @Override
    public <S extends Speaker> List<S> saveAll(Iterable<S> entities) {
        return speakerRepository.saveAll(entities);
    }

    @Override
    public void delete(Speaker entity) {
        speakerRepository.delete(entity);
    }

    @Override
    public long count() {
        return speakerRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        speakerRepository.deleteById(integer);
    }

    @Override
    public <S extends Speaker> S save(S entity) {
        return speakerRepository.save(entity);
    }

    @Override
    public Optional<Speaker> findById(Integer integer) {
        return speakerRepository.findById(integer);
    }

    @Override
    public List<Speaker> findAll(Sort sort) {
        return speakerRepository.findAll(sort);
    }

    @Override
    public <S extends Speaker> Optional<S> findOne(Example<S> example) {
        return speakerRepository.findOne(example);
    }
}
