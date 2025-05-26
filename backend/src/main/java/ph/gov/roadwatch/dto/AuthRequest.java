package ph.gov.roadwatch.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String userId;
    private String password;
}