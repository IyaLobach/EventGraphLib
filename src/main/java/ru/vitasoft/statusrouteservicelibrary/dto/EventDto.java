package ru.vitasoft.statusrouteservicelibrary.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.model.GraphEvent;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Edge edge;
    private List<GraphEvent> eventsId;
    private Map<String, Object> eventData;
}
