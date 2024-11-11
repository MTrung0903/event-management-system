package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.dto.SectionDTO;
import hcmute.fit.event_management.entity.DetailSection;
import hcmute.fit.event_management.entity.Section;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.SectionRepository;
import hcmute.fit.event_management.service.ISectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements ISectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EventRepository eventRepository;


    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<SectionDTO> getSectionOfEvent(int eventId) {
        if(eventRepository.findById(eventId).isPresent()) {
            List<Section> sections = sectionRepository.findByEventId(eventId);
            List<SectionDTO> sectionDTOs = new ArrayList<>();
            for(Section section : sections) {
                SectionDTO sectionDTO = new SectionDTO();
                sectionDTO.setId(section.getId());
                sectionDTO.setStartTime(section.getStartTime());
                sectionDTO.setEndTime(section.getEndTime());
                sectionDTO.setEventId(eventId);
                List<DetailSectionDTO> detailSectionDTOs = new ArrayList<>();
                for(DetailSection detail : section.getListDetailSections()){
                    DetailSectionDTO dto = new DetailSectionDTO();
                    dto.setId(detail.getId());
                    dto.setSpeakerTitle(detail.getSpeakerTitle());
                    dto.setSpeakerId(detail.getSpeaker().getId());
                    detailSectionDTOs.add(dto);
                }
                sectionDTO.setListDetailSections(detailSectionDTOs);
                sectionDTOs.add(sectionDTO);
            }
            return sectionDTOs;
        }
        return null;
    }

    @Override
    public SectionDTO getSectionById(int sectionId) {
        Optional<Section> section = sectionRepository.findById(sectionId);
        SectionDTO sectionDTO = new SectionDTO();
        if (section.isPresent()) {
            BeanUtils.copyProperties(section.get(), sectionDTO);
            List<DetailSectionDTO> detailSectionDTOs = new ArrayList<>();
            for(DetailSection detail : section.get().getListDetailSections()){
                DetailSectionDTO dto = new DetailSectionDTO();
                dto.setId(detail.getId());
                dto.setSpeakerTitle(detail.getSpeakerTitle());
                dto.setSpeakerId(detail.getSpeaker().getId());
                detailSectionDTOs.add(dto);
            }
            sectionDTO.setListDetailSections(detailSectionDTOs);
        } else
            System.console().printf("Section not found");
        return sectionDTO;
    }
    @Override
    public boolean addSection(int eventId, String startTimte, String endTimte)  {
        boolean isSuccess = false;
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(eventRepository.findById(eventId).isPresent() ) {
                Section section = new Section();
                Date start = formatter.parse(startTimte.trim());
                Date end = formatter.parse(endTimte.trim());
                section.setStartTime(start);
                section.setEndTime(end);
                section.setEvent(eventRepository.findById(eventId).get());
                sectionRepository.save(section);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return isSuccess;
    }
    @Override
    public boolean updateSection(int sectionId, int eventId, String startTimte, String endTimte) {
        boolean isSuccess = false;
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(eventRepository.findById(eventId).isPresent() ) {
                Section section = sectionRepository.findById(sectionId).get();
                Date start = formatter.parse(startTimte.trim());
                Date end = formatter.parse(endTimte.trim());
                section.setStartTime(start);
                section.setEndTime(end);
                section.setEvent(eventRepository.findById(eventId).get());
                sectionRepository.save(section);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteSection(int sectionId) {
        boolean isSuccess = false;
        Optional<Section> section = sectionRepository.findById(sectionId);
        if(section.isPresent()) {
            sectionRepository.deleteById(sectionId);
            isSuccess = true;
        }
        return isSuccess;
    }
}
