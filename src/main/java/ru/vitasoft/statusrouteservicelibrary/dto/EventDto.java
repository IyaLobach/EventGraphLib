package ru.vitasoft.statusrouteservicelibrary.dto;


import lombok.Builder;
import lombok.Data;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.model.Event;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class EventDto {
    private Edge edge;
    private List<Event> eventsId;
    private Map<String, Object> eventData;
}
