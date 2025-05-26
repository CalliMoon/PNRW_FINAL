package ph.gov.roadwatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Road {
    private String id;
    private String name;
    private String status;
    private LocalDate lastInspected;
    private List<String> issues = new ArrayList<>();
}
