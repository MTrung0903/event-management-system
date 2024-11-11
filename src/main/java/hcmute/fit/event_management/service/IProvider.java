package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.ProviderDTO;
import hcmute.fit.event_management.entity.Provider;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IProvider {

    List<ProviderDTO> getAllProviders();

    boolean updateProvider(Integer providerId, String name, String contact, String email, String phone, String address, String website);

    boolean addProvider(String name, String contact, String email, String phone, String address, String website);

    ProviderDTO findProviderById(Integer providerId);

    boolean deleteProvider(Integer providerId);
}
