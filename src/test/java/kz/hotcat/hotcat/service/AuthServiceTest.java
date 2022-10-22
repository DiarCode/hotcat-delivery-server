package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.LoginDTO;
import kz.hotcat.hotcat.dto.RegistrationDTO;
import kz.hotcat.hotcat.dto.TokenDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.entity.Restaurant;
import kz.hotcat.hotcat.utils.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceTest {
    @Mock
    UserDetailsManager userDetailsManager;

    @Mock
    TokenGenerator tokenGenerator;

    @Mock
    DaoAuthenticationProvider daoAuthenticationProvider;

    RegistrationDTO registrationDTO;
    LoginDTO loginDTO;

    TokenDTO tokenDTO;

    @BeforeEach
    public void setup(){
        registrationDTO = RegistrationDTO.builder()
                .email("email")
                .name("name")
                .password("pass")
                .username("username")
                .build();

        loginDTO = LoginDTO.builder()
                .usernameOrEmail("email")
                .password("pass")
                .build();

        tokenDTO = TokenDTO.builder()
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .userId("1")
                .build();
    }

    @DisplayName("Registration new user")
    @Test
    public void whenRegisterUser_thenReturnTokenDTOObject(){
        AppUser candidate = new AppUser(registrationDTO.getUsername(),
                registrationDTO.getEmail(),
                registrationDTO.getName(),
                registrationDTO.getPassword(),
                registrationDTO.isSubscribed());

        willDoNothing().given(userDetailsManager).createUser(candidate);
        userDetailsManager.createUser(candidate);

        Authentication authentication = UsernamePasswordAuthenticationToken
                .authenticated(candidate, registrationDTO.getPassword(), EMPTY_LIST);

        given(tokenGenerator.createTokens(authentication)).willReturn(tokenDTO);

        TokenDTO responseTokenDTO = tokenGenerator.createTokens(authentication);

        assertThat(tokenDTO.getUserId()).isEqualTo(responseTokenDTO.getUserId());
    }

    @DisplayName("Login user")
    @Test
    public void whenLoginUser_thenReturnTokenDTOObject(){
        Authentication authentication = daoAuthenticationProvider
                .authenticate(UsernamePasswordAuthenticationToken
                        .unauthenticated(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        given(tokenGenerator.createTokens(authentication)).willReturn(tokenDTO);

        TokenDTO tokenDTO = tokenGenerator.createTokens(authentication);

        assertThat(tokenDTO.getUserId()).isEqualTo(tokenDTO.getUserId());
    }
}