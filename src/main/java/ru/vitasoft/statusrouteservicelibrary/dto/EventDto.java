package ru.vitasoft.statusrouteservicelibrary.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
@Builder
public class EventDto {

    private Collection<Long> eventsId;
    private Map<String, Object> eventData;
}
