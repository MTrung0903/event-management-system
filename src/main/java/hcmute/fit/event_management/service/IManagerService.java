package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.entity.Manager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IManagerService {

    List<ManagerDTO> findAllManager();

    Optional<Manager> findById(Integer integer);

    Boolean updateProfile(int manId, ManagerDTO managerDTO);
}
