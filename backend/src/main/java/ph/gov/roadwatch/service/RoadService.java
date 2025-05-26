package ph.gov.roadwatch.service;

import org.springframework.stereotype.Service;
import ph.gov.roadwatch.model.Road;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoadService {
    private final Map<String, Road> roads = new HashMap<>();

    public RoadService() {
        Road road1 = new Road();
        road1.setId("1");
        road1.setName("EDSA");
        road1.setStatus("Good");
        road1.setLastInspected(LocalDate.now());
        roads.put(road1.getId(), road1);

        Road road2 = new Road();
        road2.setId("2");
        road2.setName("C5");
        road2.setStatus("Needs Repair");
        road2.setLastInspected(LocalDate.now());
        roads.put(road2.getId(), road2);
    }

    public List<Road> getAllRoads() {
        return new ArrayList<>(roads.values());
    }

    public Road getRoadById(String id) {
        return roads.get(id);
    }

    public Road addIssue(String id, String issue) {
        Road road = roads.get(id);
        if (road != null) {
            road.getIssues().add(issue);
        }
        return road;
    }
}
