package ph.gov.roadwatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private String userId;
    private String password;
    private String name;
    private String role;
    private String region;
    private LocalDateTime lastActive;
}
