package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.SectionDTO;
import hcmute.fit.event_management.entity.Section;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ISectionService {

    List<SectionDTO> getSectionOfEvent(int eventId);

    SectionDTO getSectionById(int sectionId);

    boolean addSection(int eventId, String startTimte, String endTimte) ;

    boolean updateSection(int sectionId, int eventId, String startTimte, String endTimte) ;

    boolean deleteSection(int sectionId);
}
