package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.AccountDTO;
import hcmute.fit.event_management.dto.AccountDetail;
import hcmute.fit.event_management.service.AuthService;
import hcmute.fit.event_management.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import payload.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtUtils;

    @Override
    public ResponseEntity<Response> signIn(AccountDTO account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token = jwtUtils.generateToken(authentication, roles);
            Response response = new Response(200, "Succesfully", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            Response response = new Response(401,"Unsuccessful","False");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
