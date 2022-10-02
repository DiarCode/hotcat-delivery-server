package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.LoginDTO;
import kz.hotcat.hotcat.dto.RegistrationDTO;
import kz.hotcat.hotcat.dto.TokenDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.utils.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import static java.util.Collections.EMPTY_LIST;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserDetailsManager userDetailsManager;
    private final TokenGenerator tokenGenerator;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    private final JwtAuthenticationProvider jwtRefreshTokenAuthProvider;

    public ResponseEntity registerNewUser(RegistrationDTO registrationDTO) {
        AppUser candidate = new AppUser(registrationDTO.getUsername(),
                        registrationDTO.getEmail(),
                        registrationDTO.getName(),
                        registrationDTO.getPassword());

        userDetailsManager.createUser(candidate);
        Authentication authentication = UsernamePasswordAuthenticationToken
                .authenticated(candidate, registrationDTO.getPassword(), EMPTY_LIST);

        return ResponseEntity.ok(tokenGenerator.createTokens(authentication));
    }

    public ResponseEntity loginUser(LoginDTO loginDTO) {
        Authentication authentication = daoAuthenticationProvider
                .authenticate(UsernamePasswordAuthenticationToken
                        .unauthenticated(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        return ResponseEntity.ok(tokenGenerator.createTokens(authentication));
    }

    public ResponseEntity checkOrRefreshToken(TokenDTO tokenDTO) {
        Authentication authentication = jwtRefreshTokenAuthProvider
                .authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));

        return ResponseEntity.ok(tokenGenerator.createTokens(authentication));
    }
}
