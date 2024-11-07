package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements IManagerService{
    @Autowired
    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public List<Manager> findAllById(Iterable<Integer> integers) {
        return managerRepository.findAllById(integers);
    }

    @Override
    public <S extends Manager> List<S> saveAll(Iterable<S> entities) {
        return managerRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return managerRepository.count();
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        managerRepository.deleteAllById(integers);
    }

    @Override
    public <S extends Manager> S save(S entity) {
        return managerRepository.save(entity);
    }

    @Override
    public Optional<Manager> findById(Integer integer) {
        return managerRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        managerRepository.deleteById(integer);
    }

    @Override
    public <S extends Manager> Optional<S> findOne(Example<S> example) {
        return managerRepository.findOne(example);
    }

    @Override
    public List<Manager> findAll(Sort sort) {
        return managerRepository.findAll(sort);
    }
}
