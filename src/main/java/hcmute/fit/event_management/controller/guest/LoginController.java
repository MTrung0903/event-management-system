package hcmute.fit.event_management.controller.guest;

import hcmute.fit.event_management.entity.Account;
import hcmute.fit.event_management.service.Impl.AccountServiceImpl;
import hcmute.fit.event_management.util.JwtHepler;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.Response;

import javax.crypto.SecretKey;

@RestController
public class LoginController {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    JwtHepler jwtHepler;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        Response response;
        if (accountServiceImpl.checkLogin(account.getEmail(), account.getPassword())){
            String token = jwtHepler.generateToken(account.getEmail());
            response = new Response(200,"Succesfully",token);
        }
        else {
            response = new Response(401,"Unsuccessful","False");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/test")
    public String test() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        return Encoders.BASE64.encode(key.getEncoded());
    }
}
