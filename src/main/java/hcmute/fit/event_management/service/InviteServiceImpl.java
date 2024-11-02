package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Invite;
import hcmute.fit.event_management.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InviteServiceImpl implements IInviteService{
    @Autowired
    private InviteRepository inviteRepository;

    public InviteServiceImpl(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    @Override
    public <S extends Invite> S save(S entity) {
        return inviteRepository.save(entity);
    }

    @Override
    public List<Invite> findAll(Sort sort) {
        return inviteRepository.findAll(sort);
    }

    @Override
    public List<Invite> findAll() {
        return inviteRepository.findAll();
    }

    @Override
    public List<Invite> findAllById(Iterable<Integer> integers) {
        return inviteRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return inviteRepository.count();
    }

    @Override
    public void delete(Invite entity) {
        inviteRepository.delete(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        inviteRepository.deleteById(integer);
    }

    @Override
    public <S extends Invite> Optional<S> findOne(Example<S> example) {
        return inviteRepository.findOne(example);
    }
}
