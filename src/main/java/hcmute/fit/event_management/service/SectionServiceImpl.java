package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Section;
import hcmute.fit.event_management.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements ISectionService{
    @Autowired
    private SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    @Override
    public List<Section> findAllById(Iterable<Integer> integers) {
        return sectionRepository.findAllById(integers);
    }

    @Override
    public <S extends Section> List<S> saveAll(Iterable<S> entities) {
        return sectionRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return sectionRepository.count();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        sectionRepository.deleteAllById(integers);
    }

    @Override
    public void deleteById(Integer integer) {
        sectionRepository.deleteById(integer);
    }

    @Override
    public Optional<Section> findById(Integer integer) {
        return sectionRepository.findById(integer);
    }

    @Override
    public <S extends Section> S save(S entity) {
        return sectionRepository.save(entity);
    }

    @Override
    public List<Section> findAll(Sort sort) {
        return sectionRepository.findAll(sort);
    }

    @Override
    public <S extends Section> Optional<S> findOne(Example<S> example) {
        return sectionRepository.findOne(example);
    }
}
