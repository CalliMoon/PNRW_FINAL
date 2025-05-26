package ph.gov.roadwatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.roadwatch.service.FileService;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MainController {
    private final FileService fileService = new FileService();

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String userId = credentials.get("userId");
        String password = credentials.get("password");

        if (fileService.validateUser(userId, password)) {
            Map<String, String> response = new HashMap<>();
            response.put("token", userId); // Simple token for demo
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/api/users/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        String userId = token.replace("Bearer ", "");
        Map<String, String> userDetails = fileService.getUserDetails(userId);
        if (userDetails != null) {
            fileService.updateUserLastActive(userId);
            return ResponseEntity.ok(userDetails);
        }
        return ResponseEntity.status(404).body("User not found");
    }

    @GetMapping("/api/roads")
    public ResponseEntity<?> getRoads() {
        return ResponseEntity.ok(fileService.getRoads());
    }
}