package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Mc;
import hcmute.fit.event_management.repository.McRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class McServiceImpl implements IMcService{
    @Autowired
    private McRepository mcRepository;

    public McServiceImpl(McRepository mcRepository) {
        this.mcRepository = mcRepository;
    }

    @Override
    public List<Mc> findAll() {
        return mcRepository.findAll();
    }

    @Override
    public List<Mc> findAllById(Iterable<Integer> integers) {
        return mcRepository.findAllById(integers);
    }

    @Override
    public <S extends Mc> List<S> saveAll(Iterable<S> entities) {
        return mcRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return mcRepository.count();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        mcRepository.deleteAllById(integers);
    }

    @Override
    public void deleteById(Integer integer) {
        mcRepository.deleteById(integer);
    }

    @Override
    public <S extends Mc> S save(S entity) {
        return mcRepository.save(entity);
    }

    @Override
    public Optional<Mc> findById(Integer integer) {
        return mcRepository.findById(integer);
    }

    @Override
    public List<Mc> findAll(Sort sort) {
        return mcRepository.findAll(sort);
    }

    @Override
    public <S extends Mc> Optional<S> findOne(Example<S> example) {
        return mcRepository.findOne(example);
    }
}
