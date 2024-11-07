package hcmute.fit.event_management.service;

import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements IEventService{
    @Autowired
    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findAllById(Iterable<Integer> integers) {
        return eventRepository.findAllById(integers);
    }

    @Override
    public <S extends Event> List<S> saveAll(Iterable<S> entities) {
        return eventRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return eventRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        eventRepository.deleteById(integer);
    }

    @Override
    public <S extends Event> S save(S entity) {
        return eventRepository.save(entity);
    }

    @Override
    public List<Event> findAll(Sort sort) {
        return eventRepository.findAll(sort);
    }

    @Override
    public <S extends Event> Optional<S> findOne(Example<S> example) {
        return eventRepository.findOne(example);
    }
}
