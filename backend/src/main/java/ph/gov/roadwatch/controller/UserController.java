package ph.gov.roadwatch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.roadwatch.model.User;
import ph.gov.roadwatch.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        if (userService.validateUser(loginRequest.getUserId(), loginRequest.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("token", loginRequest.getUserId()); // simple token for demo
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
        String userId = token.replace("Bearer ", "");
        User user = userService.getUserByUserId(userId);
        if (user != null) {
            userService.updateUserLastActive(userId);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(404).body("User not found");
    }
}
