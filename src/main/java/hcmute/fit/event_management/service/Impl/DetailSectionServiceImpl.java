package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.entity.DetailSection;
import hcmute.fit.event_management.repository.DetailSecyionRepository;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.SectionRepository;
import hcmute.fit.event_management.repository.SpeakerRepository;
import hcmute.fit.event_management.service.IDetailSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailSectionServiceImpl implements IDetailSectionService {
    @Autowired
    private DetailSecyionRepository detailSecyionRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    public DetailSectionServiceImpl(DetailSecyionRepository detailSecyionRepository) {
        this.detailSecyionRepository = detailSecyionRepository;
    }
    @Override
    public List<DetailSectionDTO> getListDetailSectionsByEventId(int eventId) {
        List<DetailSectionDTO> listDetailSection = new ArrayList<>();
        List<DetailSection> detailSections = detailSecyionRepository.findSectionByEventID(eventId);
        for (DetailSection detailSection : detailSections) {
            DetailSectionDTO detailSectionDTO = new DetailSectionDTO();
            detailSectionDTO.setId(detailSection.getId());
            detailSectionDTO.setSpeakerTitle(detailSection.getSpeakerTitle());
            detailSectionDTO.setSpeakerId(detailSection.getSpeaker().getId());
            listDetailSection.add(detailSectionDTO);
        }
        return listDetailSection;
    }

    @Override
    public DetailSectionDTO getDetailSectionById(int id) {
        Optional<DetailSection> detailSection = detailSecyionRepository.findById(id);
        if(detailSection.isPresent()) {
            DetailSectionDTO detailSectionDTO = new DetailSectionDTO();
            detailSectionDTO.setId(detailSection.get().getId());
            detailSectionDTO.setSpeakerTitle(detailSection.get().getSpeakerTitle());
            detailSectionDTO.setSpeakerId(detailSection.get().getSpeaker().getId());
            return detailSectionDTO;
        }
        return null;
    }

    @Override
    public boolean addDetailSection(DetailSectionDTO detail) {
        boolean isSuccess = false;
        try{
            if(speakerRepository.findById(detail.getSpeakerId()).isPresent() && sectionRepository.findById(detail.getSectionId()).isPresent()
                    &&  eventRepository.findById(detail.getEventId()).isPresent()) {
                DetailSection detailSection = new DetailSection();
                detailSection.setSpeaker(speakerRepository.findById(detail.getSpeakerId()).get());
                detailSection.setSpeakerTitle(detail.getSpeakerTitle());
                detailSection.setEvent(eventRepository.findById(detail.getEventId()).get());
                detailSection.setSection(sectionRepository.findById(detail.getSectionId()).get());
                detailSecyionRepository.save(detailSection);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("add detail section failed" +e.getMessage() );
        }
        return isSuccess;
    }
    @Override
    public boolean updateDetailSection(DetailSectionDTO detail) {
        boolean isSuccess = false;
        try{
            if(detailSecyionRepository.findById(detail.getId()).isPresent()){
                if(speakerRepository.findById(detail.getSpeakerId()).isPresent() && sectionRepository.findById(detail.getSectionId()).isPresent()
                        && speakerRepository.findById(detail.getSpeakerId()).isPresent() && eventRepository.findById(detail.getEventId()).isPresent()) {
                    DetailSection detailSection = detailSecyionRepository.findById(detail.getId()).get();
                    detailSection.setSpeaker(speakerRepository.findById(detail.getSpeakerId()).get());
                    detailSection.setSpeakerTitle(detail.getSpeakerTitle());
                    detailSection.setEvent(eventRepository.findById(detail.getEventId()).get());
                    detailSection.setSection(sectionRepository.findById(detail.getSectionId()).get());
                    detailSecyionRepository.save(detailSection);
                    isSuccess = true;
                }
            }

        } catch (Exception e) {
            System.out.println("add detail section failed" +e.getMessage() );
        }
        return isSuccess;
    }
    @Override
    public boolean deleteDetailSection(int detailId) {
        boolean isSuccess = false;
        try{
            if(detailSecyionRepository.findById(detailId).isPresent()){
                detailSecyionRepository.deleteById(detailId);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("delete detail section failed" +e.getMessage() );
        }
        return isSuccess;
    }
}
