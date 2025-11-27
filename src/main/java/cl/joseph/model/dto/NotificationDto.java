package cl.joseph.model.dto;


import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {
    private UUID id;
    private ZonedDateTime timestamp;
    private String message;
}
