package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.ProviderService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IProviderService {
    List<ProviderService> findAll(Sort sort);

    List<ProviderService> findAll();

    List<ProviderService> findAllById(Iterable<Integer> integers);

    <S extends ProviderService> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteById(Integer integer);

    Optional<ProviderService> findById(Integer integer);

    <S extends ProviderService> Optional<S> findOne(Example<S> example);
}
