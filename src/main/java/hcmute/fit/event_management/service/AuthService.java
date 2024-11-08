package hcmute.fit.event_management.service;

import hcmute.fit.event_management.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import payload.Response;

@Service
public interface AuthService {
    ResponseEntity<Response> signIn(AccountDTO account);
}
