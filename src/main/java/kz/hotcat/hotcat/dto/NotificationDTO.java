package kz.hotcat.hotcat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotificationDTO {
    private String subject;
    private String body;
}
