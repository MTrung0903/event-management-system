package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.InviteDTO;
import hcmute.fit.event_management.entity.Event;
import hcmute.fit.event_management.entity.Invite;
import hcmute.fit.event_management.repository.EventRepository;
import hcmute.fit.event_management.repository.InviteRepository;
import hcmute.fit.event_management.service.IInviteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InviteServiceImpl implements IInviteService {
    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private EventRepository eventRepository;

    public InviteServiceImpl(InviteRepository inviteRepository) {
        this.inviteRepository = inviteRepository;
    }

    @Override
    public List<InviteDTO> getListInvitesByEventId(int eventId) {
        List<Invite> invites = inviteRepository.findByEventId(eventId);
        List<InviteDTO> inviteDTOs = new ArrayList<>();
        for (Invite invite : invites) {
            InviteDTO inviteDTO = new InviteDTO();
            BeanUtils.copyProperties(invite, inviteDTO);
            inviteDTO.setEventId(eventId);
            inviteDTOs.add(inviteDTO);
        }
        return inviteDTOs;
    }
    @Override
    public InviteDTO findInviteByNameOrEmail(String inviteInfor) {
        Invite invite = inviteRepository.findByNameOrEmail(inviteInfor);
        InviteDTO inviteDTO = new InviteDTO();
        BeanUtils.copyProperties(invite, inviteDTO);
        return inviteDTO;
    }
    @Override
    public InviteDTO findInvite(int inviteId) {
        Invite invite = inviteRepository.findById(inviteId).get();
        InviteDTO inviteDTO = new InviteDTO();
        BeanUtils.copyProperties(invite, inviteDTO);
        return inviteDTO;
    }
    @Override
    public boolean addInvite(String name, String email, String inviteDate, String status,int eventId) {

        boolean isSuccess = false;
        try {
            Optional<Event> eventOpt = eventRepository.findById(eventId);
            if (eventOpt.isPresent()) {
                Event event = eventOpt.get();
                Invite newInvite = new Invite();
                newInvite.setName(name);
                newInvite.setEmail(email);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(inviteDate.trim());
                newInvite.setInviteDate(date);
                newInvite.setStatus(status);
                newInvite.setEvent(event);
                inviteRepository.save(newInvite);
                isSuccess = true;
            } else {
                throw new Exception("Event with ID " + eventId + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Add invite failed: " + e.getMessage());
            isSuccess = false;
        }
        return isSuccess;
    }
    @Override
    public boolean updateInvite(Integer inviteId, String name, String email, String inviteDate, String status) {
        Optional<Invite> invite = inviteRepository.findById(inviteId);
        boolean isUpdated = false;
        try{
            if(invite.isPresent()) {
                Invite updateInvite = invite.get();
                updateInvite.setName(name);
                updateInvite.setEmail(email);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = formatter.parse(inviteDate.trim());
                updateInvite.setInviteDate(date);
                updateInvite.setStatus(status);
                inviteRepository.save(updateInvite);
                isUpdated = true;
            }
        } catch (Exception e) {
            System.out.println("Update invite failed" +e.getMessage());
        }

        return isUpdated;
    }

    @Override
    public boolean updateInviteStatus(Integer inviteId, String status) {
        Optional<Invite> invite = inviteRepository.findById(inviteId);
        boolean isUpdated = false;
        if(invite.isPresent()) {
            Invite updateInvite = invite.get();
            updateInvite.setStatus(status);
            inviteRepository.save(updateInvite);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteInvite(Integer inviteId) {
        Optional<Invite> invite = inviteRepository.findById(inviteId);
        boolean isDeleted = false;
        if(invite.isPresent()) {
            inviteRepository.deleteById(inviteId);
            isDeleted = true;
        }
        return isDeleted;
    }
}
