package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.ProviderDTO;
import hcmute.fit.event_management.dto.ProviderServiceDTO;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.entity.Provider;
import hcmute.fit.event_management.entity.ProviderEvent;
import hcmute.fit.event_management.entity.ProviderService;
import hcmute.fit.event_management.entity.keys.ProviderEventId;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.ProviderEventRepository;
import hcmute.fit.event_management.repository.ProviderRepository;
import hcmute.fit.event_management.service.IProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public boolean addProviderForEvent(int proId, int eventId) {
        boolean isSuccess = false;

        try {
            Optional<Provider> providerOptional = providerRepository.findById(proId);
            Optional<Event> eventOptional = eventRepository.findById(eventId);

            if (providerOptional.isPresent() && eventOptional.isPresent()) {
                ProviderEvent providerEvent = new ProviderEvent();
                ProviderEventId providerEventId = new ProviderEventId(proId, eventId);

                providerEvent.setId(providerEventId);
                providerEvent.setProvider(providerOptional.get());
                providerEvent.setEvent(eventOptional.get());

                providerEventRepository.save(providerEvent);
                isSuccess = true;
            } else {
                System.out.println("Provider or Event not found for the given IDs.");
            }
        } catch (Exception e) {
            System.out.println("Add provider for event failed: " + e.getMessage());
        }

        return isSuccess;
    }

    @Override
    public List<ProviderDTO> listProviderInEvent(int eventId){
        List<ProviderDTO> providerDTOs = new ArrayList<>();
        List<ProviderEvent> list = providerEventRepository.findByEventId(eventId);
        for (ProviderEvent providerEvent : list) {
            ProviderDTO providerDTO = new ProviderDTO();
            Provider provider = providerRepository.findById(providerEvent.getProvider().getId()).get();
            BeanUtils.copyProperties(provider, providerDTO);
            providerDTOs.add(providerDTO);

        }
        return providerDTOs;
    }
    @Override
    public List<ProviderDTO> listProviderForAdd(int eventId){
        List<ProviderDTO> providerDTOs = new ArrayList<>();
        Set<Integer> oldProviderId = new HashSet<>();

        List<ProviderDTO> providers = getAllProviders();
        List<ProviderDTO> oldProvider = listProviderInEvent(eventId);


        for (ProviderDTO providerDTO : oldProvider) {
            oldProviderId.add(providerDTO.getId());
        }
        for (ProviderDTO providerDTO : providers) {
            if(!oldProviderId.contains(providerDTO.getId())) {
                providerDTOs.add(providerDTO);
            }
        }
        return providerDTOs;

    }

}
