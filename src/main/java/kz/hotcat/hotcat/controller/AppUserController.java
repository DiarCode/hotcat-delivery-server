package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/{id}")
    @PreAuthorize("#user.id == #id")
    public ResponseEntity getUserById(@AuthenticationPrincipal AppUser user, @PathVariable Long id) {
        return appUserService.getUserById(id);
    }
}
