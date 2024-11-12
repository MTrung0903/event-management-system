package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.DetailSectionDTO;
import hcmute.fit.event_management.entity.DetailSection;

import java.util.List;
import java.util.Optional;

public interface IDetailSectionService {

    List<DetailSectionDTO> getListDetailSectionsByEventId(int eventId);

    DetailSectionDTO getDetailSectionById(int id);

    boolean addDetailSection(DetailSectionDTO detail);

    boolean updateDetailSection(DetailSectionDTO detail);

    boolean deleteDetailSection(int detailId);
}
