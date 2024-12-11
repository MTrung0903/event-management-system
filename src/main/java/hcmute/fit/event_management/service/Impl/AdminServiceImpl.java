package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.EventDTO;
import hcmute.fit.event_management.dto.OverviewDTO;
import hcmute.fit.event_management.entity.Employee;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.entity.Sponsor;
import hcmute.fit.event_management.entity.SponsorEvent;
import hcmute.fit.event_management.repository.EmployeeRepository;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.SponsorRepository;
import hcmute.fit.event_management.service.IAdminService;
import hcmute.fit.event_management.service.IEventService;
import hcmute.fit.event_management.service.ISponsorEventService;
import hcmute.fit.event_management.service.ISponsorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    IEventService eventService;
    @Autowired
    ISponsorEventService sponsorEventService;

    @Override
    public OverviewDTO getOverview() {
        OverviewDTO overview = new OverviewDTO();
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO = new EventDTO();
            BeanUtils.copyProperties(event, eventDTO);
            eventDTO.setEventStart(event.getEventStart().toString());
            eventDTO.setEventEnd(event.getEventEnd().toString());
            eventDTOs.add(eventDTO);
        }
        List<Employee> employees = employeeRepository.findAll();
        List<SponsorEvent> sponsorEvents = sponsorEventService.sponsorEvents();
        int totalEventFinish,totalDevices = 0, totalEmployees = 0;
        totalEventFinish = events.stream().filter(event -> Objects.equals(event.getEventStatus(), "Completed")).toList().size();
        totalEmployees = employees.size();
        // event hoàn thành
        overview.setTotalEvents(totalEventFinish);
        // tổng số nhân viên
        overview.setTotalEmployees(totalEmployees);
        // tổng số thiết bị đang sử dụng
        overview.setTotalDevices(totalDevices);
        // list sự kiện hoàn thành theo tháng
        overview.setCntCompleted(getCompletedEventsByMonth(events));
        // list sự kiện hủy theo tháng
        overview.setCntCancel(getCancelEventsByMonth(events));
        // list sự kiện hủy theo tháng
        overview.setCntSponsor(getSponsorsByMonth(sponsorEvents));
        // list event
        overview.setListEvent(eventDTOs);
        return overview;
    }
    public int[] getCompletedEventsByMonth(List<Event> events) {
        // Bước 1: Lọc các sự kiện có status là "Hoàn thành"
        Map<Month, Long> completedEventsByMonth = events.stream()
                .filter(event -> "Completed".equals(event.getEventStatus())) // Lọc theo status
                .collect(Collectors.groupingBy(
                        event -> Month.of(event.getEventStart().getMonth()), // Nhóm theo tháng
                        Collectors.counting() // Đếm số lượng sự kiện trong mỗi tháng
                ));

        return IntStream.rangeClosed(1, 12) // Tạo range từ 1 đến 12 (tương ứng tháng 1-12)
                .map(month -> completedEventsByMonth.getOrDefault(Month.of(month), 0L).intValue()) // Lấy số lượng hoặc mặc định 0
                .toArray();
    }
    public int[] getCancelEventsByMonth(List<Event> events) {
        // Bước 1: Lọc các sự kiện có status là "Hoàn thành"
        Map<Month, Long> completedEventsByMonth = events.stream()
                .filter(event -> "Cancel".equals(event.getEventStatus())) // Lọc theo status
                .collect(Collectors.groupingBy(
                        event -> Month.of(event.getEventStart().getMonth()), // Nhóm theo tháng
                        Collectors.counting() // Đếm số lượng sự kiện trong mỗi tháng
                ));

        return IntStream.rangeClosed(1, 12) // Tạo range từ 1 đến 12 (tương ứng tháng 1-12)
                .map(month -> completedEventsByMonth.getOrDefault(Month.of(month), 0L).intValue()) // Lấy số lượng hoặc mặc định 0
                .toArray();
    }
    public int[] getSponsorsByMonth(List<SponsorEvent> sponsorEvents) {
        // Bước 1: Lọc các sự kiện có status là "Hoàn thành"
        Map<Integer, Long> uniqueSponsorsByMonth = sponsorEvents.stream()
                .collect(Collectors.groupingBy(
                        event -> {
                            // Chuyển sponsorDate từ Date sang LocalDate để lấy tháng
                            LocalDate sponsorDate = event.getEvent().getEventStart().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            return sponsorDate.getMonthValue(); // Lấy giá trị tháng
                        },
                        Collectors.mapping(SponsorEvent::getSponsor, Collectors.toSet()) // Tập hợp các sponsorId duy nhất
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size())); // Lấy kích thước của tập hợp

        // Bước 2: Chuyển map kết quả sang mảng 12 phần tử (tháng 1-12)
        int[] result = IntStream.rangeClosed(1, 12)
                .map(month -> uniqueSponsorsByMonth.getOrDefault(month, 0L).intValue())
                .toArray();

        return result;
    }
}
