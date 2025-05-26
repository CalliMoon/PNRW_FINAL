package ph.gov.roadwatch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.gov.roadwatch.model.Road;
import ph.gov.roadwatch.service.RoadService;

import java.util.List;

@RestController
@RequestMapping("/roads")
@RequiredArgsConstructor
public class RoadController {
    private final RoadService roadService;

    @GetMapping
    public ResponseEntity<List<Road>> getAllRoads() {
        return ResponseEntity.ok(roadService.getAllRoads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Road> getRoadById(@PathVariable String id) {
        Road road = roadService.getRoadById(id);
        if (road != null) {
            return ResponseEntity.ok(road);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/issues")
    public ResponseEntity<Road> addIssue(@PathVariable String id, @RequestBody String issue) {
        Road road = roadService.addIssue(id, issue);
        if (road != null) {
            return ResponseEntity.ok(road);
        }
        return ResponseEntity.notFound().build();
    }
}
