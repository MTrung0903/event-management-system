package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.ProviderDTO;
import hcmute.fit.event_management.dto.ProviderServiceDTO;
import hcmute.fit.event_management.entity.Provider;
import hcmute.fit.event_management.entity.ProviderEvent;
import hcmute.fit.event_management.entity.ProviderService;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.ProviderEventRepository;
import hcmute.fit.event_management.repository.ProviderRepository;
import hcmute.fit.event_management.service.IProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderImpl implements IProvider {
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderEventRepository providerEventRepository;

    @Autowired
    private EventRepository eventRepository;

    public ProviderImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<ProviderDTO> getAllProviders() {
        List<Provider> providers = providerRepository.findAll();
        List<ProviderDTO> providerDTOs = new ArrayList<>();

        for (Provider provider : providers) {
            ProviderDTO providerDTO = new ProviderDTO();
            BeanUtils.copyProperties(provider, providerDTO);

            List<ProviderServiceDTO> providerServiceDTOs = new ArrayList<>();
            for (ProviderService service : provider.getListProviderServices()) {
                ProviderServiceDTO serviceDTO = new ProviderServiceDTO();
                BeanUtils.copyProperties(service, serviceDTO);
                providerServiceDTOs.add(serviceDTO);
            }

            providerDTO.setListProviderServices(providerServiceDTOs);
            providerDTOs.add(providerDTO);
        }

        return providerDTOs;
    }

    @Override
    public boolean updateProvider(ProviderDTO providerDTO) {
        boolean isUpdated = false;
        if(providerRepository.findById(providerDTO.getId()).isPresent()) {
            Provider provider = providerRepository.findById(providerDTO.getId()).get();
            provider.setName(providerDTO.getName());
            provider.setContact(providerDTO.getContact());
            provider.setEmail(providerDTO.getEmail());
            provider.setPhone(providerDTO.getPhone());
            provider.setAddress(providerDTO.getAddress());
            provider.setWebsite(providerDTO.getWebsite());
            providerRepository.save(provider);
            isUpdated = true;
        }
        return isUpdated;
    }
    @Override
    public boolean addProvider(ProviderDTO providerDTO) {
        boolean isSuccess = false;
        try {
            Provider provider = new Provider();
            provider.setName(providerDTO.getName());
            provider.setContact(providerDTO.getContact());
            provider.setEmail(providerDTO.getEmail());
            provider.setPhone(providerDTO.getPhone());
            provider.setAddress(providerDTO.getAddress());
            provider.setWebsite(providerDTO.getWebsite());
            providerRepository.save(provider);
            isSuccess = true;
        } catch (Exception e) {
            System.out.println("Add provider failed" + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public ProviderDTO findProviderById(Integer providerId) {
        Optional<Provider> provider = providerRepository.findById(providerId);
        ProviderDTO providerDTO = new ProviderDTO();
        if(provider.isPresent()){
            BeanUtils.copyProperties(provider.get(), providerDTO);
            List<ProviderServiceDTO> providerServiceDTOs = new ArrayList<>();
            for(ProviderService service : provider.get().getListProviderServices()){
                ProviderServiceDTO serviceDTO = new ProviderServiceDTO();
                BeanUtils.copyProperties(service, serviceDTO);
                providerServiceDTOs.add(serviceDTO);
            }
            providerDTO.setListProviderServices(providerServiceDTOs);
        }
        return providerDTO;
    }
    @Override
    public boolean deleteProvider(Integer providerId) {
        boolean isDeleted = false;
        if(providerRepository.findById(providerId).isPresent()) {
            providerRepository.deleteById(providerId);
            isDeleted = true;
        }
        return isDeleted;
    }
    @Override
    public boolean addProviderForEvent(int proId, int eventId){
        boolean isSuccess = false;
       try{

           if(eventRepository.findById(eventId).isPresent() && providerRepository.findById(proId).isPresent()) {
               ProviderEvent providerEvent = new ProviderEvent();
               providerEvent.setEvent(eventRepository.findById(eventId).get());
               providerEvent.setProvider(providerRepository.findById(proId).get());
               //providerEvent.setProviderID(proId);
               //providerEvent.setEventID(eventId);
               providerEventRepository.save(providerEvent);
               isSuccess = true;
           }
       } catch (Exception e) {

           System.out.println("Add provider for event failed" + e.getMessage());
       }
       return isSuccess;
    }

}
