package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Attendee;
import hcmute.fit.event_management.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeServiceImpl implements IAttendeeService{
    @Autowired
    private AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    @Override
    public List<Attendee> findAllById(Iterable<Integer> integers) {
        return attendeeRepository.findAllById(integers);
    }

    @Override
    public long count() {
        return attendeeRepository.count();
    }

    @Override
    public void delete(Attendee entity) {
        attendeeRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        attendeeRepository.deleteAllById(integers);
    }

    @Override
    public void deleteById(Integer integer) {
        attendeeRepository.deleteById(integer);
    }

    @Override
    public Optional<Attendee> findById(Integer integer) {
        return attendeeRepository.findById(integer);
    }

    @Override
    public <S extends Attendee> S save(S entity) {
        return attendeeRepository.save(entity);
    }

    @Override
    public List<Attendee> findAll(Sort sort) {
        return attendeeRepository.findAll(sort);
    }

    @Override
    public <S extends Attendee> Optional<S> findOne(Example<S> example) {
        return attendeeRepository.findOne(example);
    }
}
