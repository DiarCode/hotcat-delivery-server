package kz.hotcat.hotcat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDTO {
    private String username;
    private String email;
    private String name;
    private String password;
    private boolean isSubscribed;
}
