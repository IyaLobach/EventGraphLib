package ru.vitasoft.statusrouteservicelibrary.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vitasoft.statusrouteservicelibrary.model.security.User;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdgeWalkDto {

    @NotNull(message = "Статус не может быть равен null")
    private Long statusFromId;

    @NotNull(message = "Статус не может быть равен null")
    private Long statusToId;

    @NotNull(message = "Текущий пользователь не может быть равен null")
    private User currentUser;

    @NotNull(message = "Пользователь-владелец не может быть равен null")
    private User ownerUser;

    @NotNull(message = "Данные для перехода не могут быть null")
    private Map<String, Object> eventData;
}
