package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.entity.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IEventService {

    List<EventDTO> findAll();

    void deleteById(Integer integer);

    <S extends Event> S save(S entity);

    Optional<Event> findById(Integer integer);

    List<EventDTO> findAllByManId(int manId);

    List<EventDTO> findAllByMcId(int mcId);

    List<EventDTO> fingEventInLocation(String location);

    EventDTO convertToEventDTO(Event event);
}
