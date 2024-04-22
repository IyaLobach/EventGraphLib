package ru.vitasoft.statusrouteservicelibrary.services.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.dto.EventDto;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomInternalServerErrorException;
import ru.vitasoft.statusrouteservicelibrary.model.GraphEvent;
import ru.vitasoft.statusrouteservicelibrary.repository.GraphEventRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GraphEventRunner {

    private final GraphEventRepository eventRepository;
    private final ConfigurableApplicationContext context;


    public void runEvents(EventDto eventDto) {
        for (GraphEvent event : eventDto.getEventsId()) {
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

    public GraphEvent findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new CustomConflictException("Не найдено событие для заданного id"));
    }

    @Transactional
    public GraphEvent save(GraphEvent event) {
        return eventRepository.save(event);
    }

    public List<GraphEvent> findAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public void deleteById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
