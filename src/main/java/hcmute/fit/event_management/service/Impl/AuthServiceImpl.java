package hcmute.fit.event_management.service.Impl;

import hcmute.fit.event_management.dto.AccountDTO;
import hcmute.fit.event_management.dto.ResetPasswordDTO;
import hcmute.fit.event_management.entity.Account;
import hcmute.fit.event_management.entity.PasswordResetToken;
import hcmute.fit.event_management.repository.AccountRepository;
import hcmute.fit.event_management.repository.PasswordResetTokenRepository;
import hcmute.fit.event_management.service.AuthService;
import hcmute.fit.event_management.util.JwtTokenUtil;
import jakarta.transaction.Transactional;
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

import java.util.List;
import java.util.Optional;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<Response> signIn(AccountDTO account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            String token = jwtTokenUtil.generateToken(authentication, roles);
            Response response = new Response(200, "Succesfully", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Response response = new Response(401, "Unsuccessful", "False");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<Response> sendResetPassword(String email) {
        Response response;
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            response = new Response(401, "Unsuccessful", "False");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            String token = jwtTokenUtil.generateResetToken(email);
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setAccount(account.get());
            resetToken.setToken(token);
            passwordResetTokenRepository.save(resetToken);
            emailService.sendResetEmail(email, token);
            response = new Response(200, "Succesfully", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
    @Transactional
    @Override
    public ResponseEntity<Response> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        String token = resetPasswordDTO.getToken();
        Response response;
        if (jwtTokenUtil.validateToken(token)) {
            Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository.findByToken(token);
            if (passwordResetToken.isPresent()) {
                Account account = passwordResetToken.get().getAccount();
                // Set the new password for the account
                account.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
                passwordResetTokenRepository.delete(passwordResetToken.get());
                // Remove the relationship to the deleted token to avoid cascade persistence
                account.setToken(null);
                accountRepository.save(account);
                response = new Response(200, "Successfully", "True");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response = new Response(401, "Unsuccessful", "False");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new Response(401, "Unsuccessful", "False");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
