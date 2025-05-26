package ph.gov.roadwatch.service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.LocalDateTime;

public class FileService {
    private static final String DATA_DIR = "data";
    private static final String USERS_FILE = DATA_DIR + "/users.txt";
    private static final String ROADS_FILE = DATA_DIR + "/roads.txt";

    public FileService() {
        initializeDataDirectory();
    }

    private void initializeDataDirectory() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            if (!Files.exists(Paths.get(USERS_FILE))) {
                Files.write(Paths.get(USERS_FILE), 
                    "admin|password123|Admin User|Administrator|NCR|2024-03-20T10:00:00\n".getBytes());
            }
            if (!Files.exists(Paths.get(ROADS_FILE))) {
                Files.write(Paths.get(ROADS_FILE), 
                    "1|EDSA|Good|2024-03-20|No issues\n2|C5|Needs Attention|2024-03-19|Potholes,Faded markings\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String userId, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(userId) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, String> getUserDetails(String userId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(userId)) {
                    Map<String, String> userDetails = new HashMap<>();
                    userDetails.put("userId", parts[0]);
                    userDetails.put("name", parts[2]);
                    userDetails.put("role", parts[3]);
                    userDetails.put("region", parts[4]);
                    userDetails.put("lastActive", parts[5]);
                    return userDetails;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, Object>> getRoads() {
        List<Map<String, Object>> roads = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ROADS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Map<String, Object> road = new HashMap<>();
                road.put("id", Integer.parseInt(parts[0]));
                road.put("name", parts[1]);
                road.put("status", parts[2]);
                road.put("lastInspected", parts[3]);
                road.put("issues", Arrays.asList(parts[4].split(",")));
                roads.add(road);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roads;
    }

    public void updateUserLastActive(String userId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
            List<String> updatedLines = new ArrayList<>();
            
            for (String line : lines) {
                String[] parts = line.split("\\|");
                if (parts[0].equals(userId)) {
                    parts[5] = LocalDateTime.now().toString();
                    updatedLines.add(String.join("|", parts));
                } else {
                    updatedLines.add(line);
                }
            }
            
            Files.write(Paths.get(USERS_FILE), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}