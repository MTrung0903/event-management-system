package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.ManagerDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Manager;
import hcmute.fit.event_management.repository.ManagerRepository;
import hcmute.fit.event_management.service.IManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<ManagerDTO> findAllManager(){
        List<Manager> managers = managerRepository.findAll();
        System.out.println(managers.size());
        List<ManagerDTO> managerDTOs = new ArrayList<>();
        for (Manager manager : managers) {
            ManagerDTO managerDTO = new ManagerDTO();
            BeanUtils.copyProperties(manager, managerDTO);
            managerDTOs.add(managerDTO);
        }
        return managerDTOs;
    }
    @Override
    public Optional<Manager> findById(Integer integer) {
        return managerRepository.findById(integer);
    }
    @Override
    public Boolean updateProfile(int manId, ManagerDTO managerDTO) {
        List<Manager> managers = managerRepository.findByPhone(managerDTO.getPhone());
        if (managers.isEmpty()) {
            Optional<Manager> manOpt = managerRepository.findById(manId);
            if (manOpt.isPresent()) {
                Manager man = manOpt.get();
                man.setPhone(managerDTO.getPhone());
                man.setAddress(managerDTO.getAddress());
                managerRepository.save(man);
                return true;
            }
        }
        return false;
    }
}
