package ph.gov.roadwatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponse {
    private String userId;
    private String name;
    private String role;
    private String region;
    private LocalDateTime lastActive;
}