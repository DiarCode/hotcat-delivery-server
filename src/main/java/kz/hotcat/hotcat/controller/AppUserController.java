package kz.hotcat.hotcat.controller;

import kz.hotcat.hotcat.dto.NotificationDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.entity.Order;
import kz.hotcat.hotcat.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}/orders")
    @PreAuthorize("#user.id == #id")
    public List<Order> getUserOrders(@AuthenticationPrincipal AppUser user, @PathVariable Long id) {
        return appUserService.getUserOrders(id);
    }

    @GetMapping("/{id}/role")
    @PreAuthorize("#user.id == #id")
    public String getUserRole(@AuthenticationPrincipal AppUser user, @PathVariable Long id) {
        return appUserService.getUserRole(id);
    }

    @PostMapping("/notify")
    public void notifySubscribedUsers(@RequestBody NotificationDTO notificationDTO) {
        appUserService.notifySubscribedUsers(notificationDTO);
    }
}
