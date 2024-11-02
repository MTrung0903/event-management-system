package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Team;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ITeamService {
    List<Team> findAll();

    List<Team> findAllById(Iterable<Integer> integers);

    <S extends Team> List<S> saveAll(Iterable<S> entities);

    long count();

    void delete(Team entity);

    void deleteById(Integer integer);

    Optional<Team> findById(Integer integer);

    <S extends Team> S save(S entity);

    List<Team> findAll(Sort sort);

    <S extends Team> Optional<S> findOne(Example<S> example);
}
