package ru.vitasoft.statusrouteservicelibrary.services.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.dto.EventDto;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomInternalServerErrorException;
import ru.vitasoft.statusrouteservicelibrary.model.Event;
import ru.vitasoft.statusrouteservicelibrary.repository.EventRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventRunnerService {

    private final EventRepository eventRepository;
    private final ConfigurableApplicationContext context;


    public void runEvents(EventDto eventDto) {
        for (Event event : eventDto.getEventsId()) {
            Object bean = context.getBean(event.getBeanName());
            String beanMethodName = event.getBeanMethod();
            try {
                Method beanMethod = bean.getClass().getDeclaredMethod(beanMethodName, Map.class);
                beanMethod.invoke(bean, eventDto.getEventData());
            } catch (Exception exception) {
                throw new CustomInternalServerErrorException(exception.getMessage());
            }
        }
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new CustomConflictException("Не найдено событие для заданного id"));
    }

    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
