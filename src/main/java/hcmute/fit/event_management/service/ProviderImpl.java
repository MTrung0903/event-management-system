package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Provider;
import hcmute.fit.event_management.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderImpl implements IProvider {
    @Autowired
    private ProviderRepository providerRepository;

    public ProviderImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public List<Provider> findAllById(Iterable<Integer> integers) {
        return providerRepository.findAllById(integers);
    }

    @Override
    public <S extends Provider> List<S> saveAll(Iterable<S> entities) {
        return providerRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return providerRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        providerRepository.deleteById(integer);
    }

    @Override
    public Optional<Provider> findById(Integer integer) {
        return providerRepository.findById(integer);
    }

    @Override
    public <S extends Provider> S save(S entity) {
        return providerRepository.save(entity);
    }

    @Override
    public List<Provider> findAll(Sort sort) {
        return providerRepository.findAll(sort);
    }

    @Override
    public <S extends Provider> Optional<S> findOne(Example<S> example) {
        return providerRepository.findOne(example);
    }
}
