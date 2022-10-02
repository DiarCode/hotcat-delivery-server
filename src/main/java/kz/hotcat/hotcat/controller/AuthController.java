package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.LoginDTO;
import kz.hotcat.hotcat.dto.RegistrationDTO;
import kz.hotcat.hotcat.dto.TokenDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody RegistrationDTO registrationDTO){
        return authService.registerNewUser(registrationDTO);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDTO loginDTO){
        return authService.loginUser(loginDTO);
    }

    @PostMapping("/token")
    public ResponseEntity checkOrRefreshToken(@RequestBody TokenDTO tokenDTO){
        return authService.checkOrRefreshToken(tokenDTO);
    }
}
