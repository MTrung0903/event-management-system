package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.ProviderServiceDTO;
import hcmute.fit.event_management.entity.ProviderService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IProviderService {

    List<ProviderServiceDTO> getServiceProviders(int providerId);

    ProviderServiceDTO findServiceById(int serviceId);

    boolean addServiceProvider(int providerId, String serviceType, String serviceName,
                               String serviceDesc, String price, String duration);

    public boolean updateServiceProvider(int serviceId, String serviceType, String serviceName,
                                         String serviceDesc, String price, String duration, int providerId);

    boolean deleteServiceProvider(int serviceId);
}
