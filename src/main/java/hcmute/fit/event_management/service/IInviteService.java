package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Invite;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IInviteService {
    <S extends Invite> S save(S entity);

    List<Invite> findAll(Sort sort);

    List<Invite> findAll();

    List<Invite> findAllById(Iterable<Integer> integers);

    long count();

    void delete(Invite entity);

    void deleteById(Integer integer);

    <S extends Invite> Optional<S> findOne(Example<S> example);
}
