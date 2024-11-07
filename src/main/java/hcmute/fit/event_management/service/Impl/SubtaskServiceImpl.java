package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.entity.SubTask;
import hcmute.fit.event_management.repository.SubtaskRepository;
import hcmute.fit.event_management.service.ISubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubtaskServiceImpl implements ISubtaskService {
    @Autowired
    private SubtaskRepository subtaskRepository;

    public SubtaskServiceImpl(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public List<SubTask> findAll() {
        return subtaskRepository.findAll();
    }

    @Override
    public List<SubTask> findAllById(Iterable<Integer> integers) {
        return subtaskRepository.findAllById(integers);
    }

    @Override
    public <S extends SubTask> List<S> saveAll(Iterable<S> entities) {
        return subtaskRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer integer) {
        subtaskRepository.deleteById(integer);
    }

    @Override
    public Optional<SubTask> findById(Integer integer) {
        return subtaskRepository.findById(integer);
    }

    @Override
    public <S extends SubTask> S save(S entity) {
        return subtaskRepository.save(entity);
    }

    @Override
    public List<SubTask> findAll(Sort sort) {
        return subtaskRepository.findAll(sort);
    }

    @Override
    public <S extends SubTask> Optional<S> findOne(Example<S> example) {
        return subtaskRepository.findOne(example);
    }
}
