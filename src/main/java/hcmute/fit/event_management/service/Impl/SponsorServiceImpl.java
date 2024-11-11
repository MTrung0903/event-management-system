package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.SponsorDTO;
import hcmute.fit.event_management.dto.SponsorEventDTO;
import hcmute.fit.event_management.entity.Sponsor;
import hcmute.fit.event_management.entity.SponsorEvent;
import hcmute.fit.event_management.repository.SponsorRepository;
import hcmute.fit.event_management.repository.SponsorShipRepository;
import hcmute.fit.event_management.service.ISponsorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorServiceImpl implements ISponsorService {
    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private SponsorShipRepository sponsorShipRepository;

    public SponsorServiceImpl(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }
    @Override
    public List<SponsorDTO> getAllSponsors() {
        List<Sponsor> sponsors = sponsorRepository.findAll();
        List<SponsorDTO> sponsorDTOs = new ArrayList<>();
        for (Sponsor sponsor : sponsors) {
            SponsorDTO sponsorDTO = new SponsorDTO();
            sponsorDTO.setId(sponsor.getId());
            BeanUtils.copyProperties(sponsor, sponsorDTO);
            sponsorDTO.setSponsorshipId(sponsor.getSponsorship().getSponsorShipID());
            sponsorDTOs.add(sponsorDTO);
            List<SponsorEventDTO> dtoList = new ArrayList<>();
            for(SponsorEvent se : sponsor.getListSponsorEvents()){
                SponsorEventDTO seDto = new SponsorEventDTO();
                seDto.setEventId(se.getEvent().getEventID());
                seDto.setEventName(se.getEvent().getEventName());
                dtoList.add(seDto);
            }
            sponsorDTO.setListSponsorEvents(dtoList);
        }
        return sponsorDTOs;
    }
    @Override
    public SponsorDTO getSponsorById(int id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        SponsorDTO sponsorDTO = new SponsorDTO();
        if(sponsor.isPresent()){
            BeanUtils.copyProperties(sponsor.get(), sponsorDTO);
            sponsorDTO.setSponsorshipId(sponsor.get().getSponsorship().getSponsorShipID());
            List<SponsorEventDTO> dtoList = new ArrayList<>();
            for(SponsorEvent se : sponsor.get().getListSponsorEvents()){
                SponsorEventDTO seDto = new SponsorEventDTO();
                seDto.setEventId(se.getEvent().getEventID());
                seDto.setEventName(se.getEvent().getEventName());
                dtoList.add(seDto);
            }
            sponsorDTO.setListSponsorEvents(dtoList);
        }
        return sponsorDTO;
    }
    @Override
    public boolean addSponsor(String name, String contact,
                              String email, String phone, String website, String address, int sponsorShipId) {
        boolean isSuccess = false;
        try{
            if(sponsorShipRepository.findById(sponsorShipId).isPresent()){
                Sponsor sponsor = new Sponsor();
                sponsor.setName(name);
                sponsor.setContact(contact);
                sponsor.setEmail(email);
                sponsor.setPhone(phone);
                sponsor.setWebsite(website);
                sponsor.setAddress(address);
                sponsor.setSponsorship(sponsorShipRepository.findById(sponsorShipId).get());
                sponsorRepository.save(sponsor);
                isSuccess = true;
            }
        }
        catch(Exception e){
            System.out.println("add sponsor failed"+ e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updatSponsor(int id, String name, String contact,
                                String email, String phone, String website, String address, int sponsorShipId) {
        boolean isSuccess = false;
        try{
            if(sponsorShipRepository.findById(sponsorShipId).isPresent()){
                Sponsor sponsor = sponsorRepository.findById(id).get();
                sponsor.setId(id);
                sponsor.setName(name);
                sponsor.setContact(contact);
                sponsor.setEmail(email);
                sponsor.setPhone(phone);
                sponsor.setWebsite(website);
                sponsor.setAddress(address);
                sponsor.setSponsorship(sponsorShipRepository.findById(sponsorShipId).get());
                sponsorRepository.save(sponsor);
                isSuccess = true;
            }
        }
        catch(Exception e){
            System.out.println("update sponsor failed"+ e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean deleteSponsor(int id) {
        boolean isSuccess = false;
        if(sponsorRepository.findById(id).isPresent()){
            sponsorRepository.deleteById(id);
            isSuccess = true;
        }
        return isSuccess;
    }
}
