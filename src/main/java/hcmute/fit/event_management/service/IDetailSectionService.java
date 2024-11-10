package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.entity.DetailSection;

import java.util.List;
import java.util.Optional;

public interface IDetailSectionService {

    List<DetailSectionDTO> getListDetailSectionsByEventId(int eventId);

    DetailSectionDTO getDetailSectionById(int id);

    boolean addDetailSection(int speakerId, String speakerTitle, int eventId, int sectionId);

    boolean updateDetailSection(int detailId, int speakerId, String speakerTitle, int eventId, int sectionId);

    boolean deleteDetailSection(int detailId);
}
