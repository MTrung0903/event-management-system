package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.service.IEventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(convertToEventDTO(event));
        }
        return eventDTOs;
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
    public Optional<Event> findById(Integer integer) {
        return eventRepository.findById(integer);
    }

    @Override
    public List<EventDTO> findAllByManId(int manId) {
        List<Event> events = eventRepository.findByManagerId(manId);
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(convertToEventDTO(event));
        }
        return eventDTOs;
    }

    @Override
    public List<EventDTO> findAllByMcId(int mcId) {
        List<Event> events = eventRepository.findByMcId(mcId);
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(convertToEventDTO(event));
        }
        return eventDTOs;
    }

    @Override
    public List<EventDTO> fingEventInLocation(String location){
        List<Event> events= eventRepository.findByLocation(location);
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            eventDTOs.add(convertToEventDTO(event));
        }
        return eventDTOs;
    }

    @Override
    public EventDTO convertToEventDTO(Event event){
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        return eventDTO;
    }
}
