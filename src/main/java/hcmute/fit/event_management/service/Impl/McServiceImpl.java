package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.dto.McDTO;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.entity.Mc;
import hcmute.fit.event_management.repository.McRepository;
import hcmute.fit.event_management.service.IMcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class McServiceImpl implements IMcService {
    @Autowired
    private McRepository mcRepository;

    public McServiceImpl(McRepository mcRepository) {
        this.mcRepository = mcRepository;
    }

    @Override
    public List<McDTO> getListMc(){
        List<McDTO> listMc = new ArrayList<>();
        List<Mc> mcList = mcRepository.findAll();
        for (Mc mc : mcList) {
            McDTO mcDTO = new McDTO();
            BeanUtils.copyProperties(mc,mcDTO);
            List<EventDTO> listeventdto = new ArrayList<>();
            for(Event e : mc.getListEvents() ){
                EventDTO eventDTO = new EventDTO();
                BeanUtils.copyProperties(e,eventDTO);
                listeventdto.add(eventDTO);
            }
            mcDTO.setListEvents(listeventdto);
            listMc.add(mcDTO);
        }
        return listMc;
    }
    @Override
    public boolean addMc(McDTO mcDto){
        boolean isSucess = false;
        try{
            Mc mc = new Mc();
            mc.setMcName(mcDto.getMcName());
            mc.setEmail(mcDto.getEmail());
            mcRepository.save(mc);
            isSucess = true;
        } catch (Exception e) {
            System.out.println("Add MC failed" + e.getMessage());
        }
        return isSucess;
    }
    @Override
    public boolean updateMc(McDTO mcDto){
        boolean isSucess = false;
        try{
            Optional<Mc> mc = mcRepository.findById(mcDto.getMcID());
            if(mc.isPresent()){
                Mc newMc = mc.get();
                newMc.setMcName(mcDto.getMcName());
                newMc.setEmail(mcDto.getEmail());
                mcRepository.save(newMc);
                isSucess = true;
            }

        } catch (Exception e) {
            System.out.println("Update MC failed" + e.getMessage());
        }
        return isSucess;
    }
    @Override
    public boolean deleteMc(Integer mcId){
        boolean isSucess = false;
        try{
            Optional<Mc> mc = mcRepository.findById(mcId);
            if(mc.isPresent()){
                mcRepository.deleteById(mcId);
                isSucess = true;
            }

        } catch (Exception e) {
            System.out.println("Delete MC failed" + e.getMessage());
        }
        return isSucess;
    }
    @Override
    public McDTO findMcById(Integer mcId){
        Optional<Mc> mc = mcRepository.findById(mcId);
        McDTO mcDTO = new McDTO();
        try{
            if(mc.isPresent()){
                BeanUtils.copyProperties(mc.get(),mcDTO);
                List<EventDTO> listeventdto = new ArrayList<>();
                for(Event e : mc.get().getListEvents()){
                    EventDTO eventDTO = new EventDTO();
                    BeanUtils.copyProperties(e,eventDTO);
                    listeventdto.add(eventDTO);
                }
                mcDTO.setListEvents(listeventdto);
            }
        } catch (Exception e) {
            System.out.println("Find MC failed" + e.getMessage());
        }
       return mcDTO;
    }
}
