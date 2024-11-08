package hcmute.fit.event_management.controller.guest;

import hcmute.fit.event_management.dto.AccountDTO;
import hcmute.fit.event_management.service.Impl.AccountServiceImpl;
import hcmute.fit.event_management.service.Impl.AuthServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;
import java.io.Console;


@RestController
public class LoginController {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO account) {
        return authServiceImpl.signIn(account);
    }
    @GetMapping("/test")
    public String test() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        return Encoders.BASE64.encode(key.getEncoded());
    }
}
