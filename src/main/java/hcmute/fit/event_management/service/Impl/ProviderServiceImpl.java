package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.ProviderServiceDTO;
import hcmute.fit.event_management.entity.ProviderService;
import hcmute.fit.event_management.repository.ProviderRepository;
import hcmute.fit.event_management.repository.ProviderServiceRepository;
import hcmute.fit.event_management.service.IProviderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements IProviderService {
    @Autowired
    private ProviderServiceRepository providerServiceRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderServiceRepository providerServiceRepository) {
        this.providerServiceRepository = providerServiceRepository;
    }

    @Override
    public List<ProviderServiceDTO> getServiceProviders(int providerId) {
        List<ProviderService> providerServiceList = providerServiceRepository.findByProviderId(providerId);
        List<ProviderServiceDTO> providerServiceDTOList = new ArrayList<>();
        for (ProviderService providerService : providerServiceList) {
            ProviderServiceDTO serviceDTO = new ProviderServiceDTO();
            BeanUtils.copyProperties(providerService, serviceDTO);
            serviceDTO.setProviderId(providerService.getProvider().getId());
            providerServiceDTOList.add(serviceDTO);
        }
        return providerServiceDTOList;
    }
    @Override
    public ProviderServiceDTO findServiceById(int serviceId){
        Optional<ProviderService> providerService = providerServiceRepository.findById(serviceId);
        ProviderServiceDTO providerServiceDTO = new ProviderServiceDTO();
        if(providerService.isPresent()){
            BeanUtils.copyProperties(providerService.get(), providerServiceDTO);
            providerServiceDTO.setProviderId(providerService.get().getProvider().getId());
            return providerServiceDTO;
        }
        return null;
    }
    @Override
    public boolean addServiceProvider(int providerId, String serviceType, String serviceName,
                                      String serviceDesc, String price, String duration) {
        boolean isSuccess = false;
        try{
            if(providerRepository.findById(providerId).isPresent()){
                ProviderService providerService = new ProviderService();
                providerService.setServiceName(serviceName);
                providerService.setServiceType(serviceType);
                providerService.setServiceDesc(serviceDesc);
                providerService.setPrice(price);
                providerService.setDuration(duration);
                providerService.setProvider(providerRepository.findById(providerId).get());
                providerServiceRepository.save(providerService);
                isSuccess = true;
            }else{
                throw new Exception("Not found provider");
            }
        } catch (Exception e) {
            System.out.println("Add service failed "+ e.getMessage());
        }
        return isSuccess;
    }
    @Override
    public boolean updateServiceProvider(int serviceId, String serviceType, String serviceName,
                                         String serviceDesc, String price, String duration, int providerId) {
        boolean isSuccess = false;
        try{
            if(providerServiceRepository.findById(serviceId).isPresent()){
                ProviderService providerService = providerServiceRepository.findById(serviceId).get();
                providerService.setServiceName(serviceName);
                providerService.setServiceType(serviceType);
                providerService.setServiceDesc(serviceDesc);
                providerService.setPrice(price);
                providerService.setDuration(duration);
                providerService.setProvider(providerRepository.findById(providerId).get());
                providerServiceRepository.save(providerService);
                isSuccess = true;
            }else{
                throw new Exception("Not found service");
            }
        } catch (Exception e) {
            System.out.println("Update service failed "+ e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteServiceProvider(int serviceId) {
        boolean isSuccess = false;
        try
        {
           if(providerServiceRepository.findById(serviceId).isPresent()){
               providerServiceRepository.deleteById(serviceId);
               isSuccess = true;
           }

        } catch (Exception e) {
            System.out.println("Delete service failed "+ e.getMessage());
        }
        return isSuccess;
    }
}
