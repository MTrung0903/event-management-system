package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SpeakerDTO;
import hcmute.fit.event_management.entity.Speaker;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ISpeakerService {

    List<SpeakerDTO> getAllSpeakers();

    SpeakerDTO getSpeakerById(int id);

    boolean addSpeaker(String name, String email, String title,
                       String phone, String address, String description);

    boolean updateSpeaker(int id, String name, String email, String title,
                          String phone, String address, String description);

    boolean deleteSpeaker(int id);
}
