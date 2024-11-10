package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SponsorShipDTO;
import hcmute.fit.event_management.entity.SponsorShip;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISponsorShipService {

    List<SponsorShipDTO> getAllSponsorShips();

    SponsorShipDTO getSponsorShipByID(int sponsorShipID);

    boolean addSponsorShip(String level, String benefit);

    boolean updateSponsorShip(int id, String level, String benefit);

    boolean deleteSponsorShip(int id);
}
