package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.dto.SpeakerDTO;
import hcmute.fit.event_management.entity.DetailSection;
import hcmute.fit.event_management.entity.Speaker;
import hcmute.fit.event_management.repository.SpeakerRepository;
import hcmute.fit.event_management.service.ISpeakerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements ISpeakerService {
    @Autowired
    private SpeakerRepository speakerRepository;

    public SpeakerServiceImpl(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @Override
    public List<SpeakerDTO> getAllSpeakers() {
        List<Speaker> speakers = speakerRepository.findAll();
        List<SpeakerDTO> speakerDTOs = new ArrayList<>();
        for (Speaker speaker : speakers) {
            SpeakerDTO speakerDTO = new SpeakerDTO();
            BeanUtils.copyProperties(speaker, speakerDTO);
            List<DetailSectionDTO> detailSectionDTOs = new ArrayList<>();
            for (DetailSection dt : speaker.getListDetailSections()){
                DetailSectionDTO dto = new DetailSectionDTO();
                BeanUtils.copyProperties(dt, dto);
            }
            speakerDTO.setListDetailSections(detailSectionDTOs);
            speakerDTOs.add(speakerDTO);
        }
        return speakerDTOs;
    }
    @Override
    public SpeakerDTO getSpeakerById(int id) {
        Optional<Speaker> speaker = speakerRepository.findById(id);
        SpeakerDTO speakerDTO = new SpeakerDTO();
        if (speaker.isPresent()) {
            BeanUtils.copyProperties(speaker.get(), speakerDTO);
            return speakerDTO;
        }
        return null;
    }
    @Override
    public boolean addSpeaker(SpeakerDTO speakerDTO){
        boolean isSuccess = false;
        try{
            Speaker speaker = new Speaker();
            speaker.setName(speakerDTO.getName());
            speaker.setEmail(speakerDTO.getEmail());
            speaker.setTitle(speakerDTO.getTitle());
            speaker.setPhone(speakerDTO.getPhone());
            speaker.setAddress(speakerDTO.getAddress());
            speaker.setDescription(speakerDTO.getDescription());
            speakerRepository.save(speaker);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("add speaker failed" + e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateSpeaker(SpeakerDTO speakerDTO){
        boolean isSuccess = false;
        try{
            if(speakerRepository.findById(speakerDTO.getId()).isPresent()){
                Speaker speaker = speakerRepository.findById(speakerDTO.getId()).get();
                speaker.setName(speakerDTO.getName());
                speaker.setEmail(speakerDTO.getEmail());
                speaker.setTitle(speakerDTO.getTitle());
                speaker.setPhone(speakerDTO.getPhone());
                speaker.setAddress(speakerDTO.getAddress());
                speaker.setDescription(speakerDTO.getDescription());
                speakerRepository.save(speaker);
                isSuccess = true;
            }else
                throw new Exception("Speaker not found");

        } catch (Exception e) {
            System.out.println("add speaker failed" + e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteSpeaker(int id){
        boolean isSuccess = false;
        try
        {
            if(speakerRepository.findById(id).isPresent()){
                speakerRepository.deleteById(id);
                isSuccess = true;
            }
        } catch (Exception e) {
            System.out.println("Delete speaker failed" + e.getMessage());
        }
        return isSuccess;
    }
}
