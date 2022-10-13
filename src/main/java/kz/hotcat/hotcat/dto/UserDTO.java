package kz.hotcat.hotcat.dto;

import kz.hotcat.hotcat.entity.AppUser;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String role;

    public static UserDTO from(AppUser user) {
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
