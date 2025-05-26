package ph.gov.roadwatch.service;

import org.springframework.stereotype.Service;
import ph.gov.roadwatch.model.User;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    // Demo: Add a default user
    public UserService() {
        User user = new User();
        user.setUserId("admin");
        user.setPassword("admin"); // plaintext for demo only!
        user.setName("Admin User");
        user.setRole("ADMIN");
        user.setRegion("NCR");
        user.setLastActive(LocalDateTime.now());
        users.put(user.getUserId(), user);
    }

    public User getUserByUserId(String userId) {
        return users.get(userId);
    }

    public boolean validateUser(String userId, String password) {
        User user = users.get(userId);
        return user != null && user.getPassword().equals(password);
    }

    public void updateUserLastActive(String userId) {
        User user = users.get(userId);
        if (user != null) {
            user.setLastActive(LocalDateTime.now());
        }
    }
}
