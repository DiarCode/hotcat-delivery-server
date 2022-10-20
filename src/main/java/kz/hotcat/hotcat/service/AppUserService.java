package kz.hotcat.hotcat.service;

import kz.hotcat.hotcat.dto.NotificationDTO;
import kz.hotcat.hotcat.dto.UserDTO;
import kz.hotcat.hotcat.entity.AppUser;
import kz.hotcat.hotcat.entity.Order;
import kz.hotcat.hotcat.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final OrderService orderService;
    private final EmailSenderService emailSenderService;

    public ResponseEntity getUserById(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = UserDTO.from(user);

        return ResponseEntity.ok(userDTO);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderService.getAllRecentOrdersByUserId(userId);
    }

    public Long getTotalUsersAmount() {
        return appUserRepository.getAmountOfUsers();
    }

    public int getSubscribedUsersAmount() {
        List<AppUser> appUserList = appUserRepository.getAllSubscribedUsers();
        return appUserList.size();
    }

    public String getUserRole(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getRole();
    }

    public void notifySubscribedUsers(NotificationDTO notificationDTO) {
        String[] subscribedUsersEmailList = appUserRepository.getAllSubscribedUsers()
                .stream()
                .map(AppUser::getEmail)
                .toArray(String[]::new);

        emailSenderService.sendEmail(subscribedUsersEmailList,
                notificationDTO.getSubject(),
                notificationDTO.getBody());
    }
}