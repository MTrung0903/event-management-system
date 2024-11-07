package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.entity.ProviderService;
import hcmute.fit.event_management.repository.ProviderServiceRepository;
import hcmute.fit.event_management.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements IProviderService {
    @Autowired
    private ProviderServiceRepository providerServiceRepository;

    public ProviderServiceImpl(ProviderServiceRepository providerServiceRepository) {
        this.providerServiceRepository = providerServiceRepository;
    }

    @Override
    public List<ProviderService> findAll(Sort sort) {
        return providerServiceRepository.findAll(sort);
    }

    @Override
    public List<ProviderService> findAll() {
        return providerServiceRepository.findAll();
    }

    @Override
    public List<ProviderService> findAllById(Iterable<Integer> integers) {
        return providerServiceRepository.findAllById(integers);
    }

    @Override
    public <S extends ProviderService> List<S> saveAll(Iterable<S> entities) {
        return providerServiceRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return providerServiceRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        providerServiceRepository.deleteById(integer);
    }

    @Override
    public Optional<ProviderService> findById(Integer integer) {
        return providerServiceRepository.findById(integer);
    }

    @Override
    public <S extends ProviderService> Optional<S> findOne(Example<S> example) {
        return providerServiceRepository.findOne(example);
    }
}
