package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Team;
import hcmute.fit.event_management.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllById(Iterable<Integer> integers) {
        return teamRepository.findAllById(integers);
    }

    @Override
    public <S extends Team> List<S> saveAll(Iterable<S> entities) {
        return teamRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return teamRepository.count();
    }

    @Override
    public void delete(Team entity) {
        teamRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        teamRepository.deleteById(integer);
    }

    @Override
    public Optional<Team> findById(Integer integer) {
        return teamRepository.findById(integer);
    }

    @Override
    public <S extends Team> S save(S entity) {
        return teamRepository.save(entity);
    }

    @Override
    public List<Team> findAll(Sort sort) {
        return teamRepository.findAll(sort);
    }

    @Override
    public <S extends Team> Optional<S> findOne(Example<S> example) {
        return teamRepository.findOne(example);
    }
}
