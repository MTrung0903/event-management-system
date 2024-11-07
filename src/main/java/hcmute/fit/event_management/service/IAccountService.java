package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> findAll();

    List<Account> findAllById(Iterable<Integer> integers);

    long count();

    void delete(Account entity);

    void deleteAll();

    void deleteAllById(Iterable<? extends Integer> integers);

    <S extends Account> S save(S entity);

    List<Account> findAll(Sort sort);

    <S extends Account> Optional<S> findOne(Example<S> example);
}
