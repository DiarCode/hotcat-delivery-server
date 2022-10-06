package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.UserDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    public ResponseEntity getUserById(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = UserDTO.from(user);

        return ResponseEntity.ok(userDTO);
    }

}
