package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.ManagerRepository;
import hcmute.fit.event_management.repository.McRepository;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.IFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private McRepository mcRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private IFileService fileService;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO = new EventDTO();
            BeanUtils.copyProperties(event, eventDTO);
            eventDTO.setManId(event.getManager().getManID());
            eventDTO.setMcId(event.getMc().getMcID());
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    @Override
    public EventDTO getEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            EventDTO eventDTO = new EventDTO();
            BeanUtils.copyProperties(event.get(), eventDTO);
            eventDTO.setManId(event.get().getManager().getManID());
            eventDTO.setMcId(event.get().getMc().getMcID());
            return eventDTO;
        }
        return null;
    }

    @Override
    public boolean addEvent(MultipartFile image, EventDTO eventDTO) {
        boolean isSuccess = false;
        boolean isUoloadImg = fileService.saveFiles(image);
        if(isUoloadImg) {
            try{

                if(managerRepository.findById(eventDTO.getManId()).isPresent()
                        && mcRepository.findById(eventDTO.getMcId()).isPresent()) {
                    Event event = new Event();
                    BeanUtils.copyProperties(eventDTO, event);
                    event.setEventImg(image.getOriginalFilename());
                    event.setManager(managerRepository.findById(eventDTO.getManId()).get());
                    event.setMc(mcRepository.findById(eventDTO.getMcId()).get());
                    eventRepository.save(event);
                    isSuccess = true;
                }
            } catch (Exception e) {
                System.out.println("add event failed" +e.getMessage());
            }
        }

        return isSuccess;

    }
    @Override
    public boolean updateEvent(MultipartFile image, EventDTO eventDTO) {
        boolean isSuccess = false;
        boolean isUoloadImg = fileService.saveFiles(image);
        if(isUoloadImg) {
            try{
                if(managerRepository.findById(eventDTO.getManId()).isPresent()
                        && mcRepository.findById(eventDTO.getMcId()).isPresent()) {
                    Event event = eventRepository.findById(eventDTO.getEventId()).get();
                    BeanUtils.copyProperties(eventDTO, event);
                    event.setEventImg(image.getOriginalFilename());
                    event.setManager(managerRepository.findById(eventDTO.getManId()).get());
                    event.setMc(mcRepository.findById(eventDTO.getMcId()).get());
                    eventRepository.save(event);
                    isSuccess = true;
                }
            } catch (Exception e) {
                System.out.println("add event failed" +e.getMessage());
            }
        }
        return isSuccess;
    }

    @Override
    public boolean addMc(int eventId, int mcId) {
        boolean isSuccess = false;
        try{
            if(mcRepository.findById(mcId).isPresent()){
                Event event = eventRepository.findById(eventId).get();
                event.setMc(mcRepository.findById(mcId).get());
                eventRepository.save(event);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("add mc failed" +e.getMessage());
        }
        return isSuccess;
    }

}
