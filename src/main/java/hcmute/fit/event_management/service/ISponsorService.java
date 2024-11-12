package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SponsorDTO;
import hcmute.fit.event_management.entity.Sponsor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISponsorService {

    List<SponsorDTO> getAllSponsors();

    SponsorDTO getSponsorById(int id);

    boolean addSponsor(SponsorDTO sponsorDTO);

    boolean updatSponsor(SponsorDTO sponsorDTO);

    boolean deleteSponsor(int id);

    boolean addSponsorForEvent(int sponsorId, int eventId);
}
