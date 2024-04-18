package ru.vitasoft.statusrouteservicelibrary.services.edge;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomBadRequestException;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.model.Event;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;
import ru.vitasoft.statusrouteservicelibrary.repository.EdgeRepository;
import org.springframework.stereotype.Service;
import ru.vitasoft.statusrouteservicelibrary.dto.EdgeWalkDto;
import ru.vitasoft.statusrouteservicelibrary.dto.EventDto;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.services.event.EventRunnerService;
import ru.vitasoft.statusrouteservicelibrary.services.permission.PermissionCheckerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EdgeWalkerService implements EdgeWalker {

    private final EdgeRepository edgeRepository;
    private final EventRunnerService eventRunnerService;
    private final Validator validator;
    private PermissionCheckerService permissionChecker;


    @Override
    public void edgeWalk(EdgeWalkDto edgeWalkDto) {
        validate(edgeWalkDto);
        Edge edge = edgeRepository.findByStatusFromIdAndStatusToId(edgeWalkDto.getStatusFromId(), edgeWalkDto.getStatusToId())
                .orElseThrow(() -> new CustomConflictException("Переход из текущего статуса в заданный невозможен"));
        permissionChecker.checkPermission(edge.getPermission(), edgeWalkDto);
        eventRunnerService.runEvents(EventDto.builder()
                .edge(edge)
                .eventsId(edge.getEvents() == null ? Collections.emptyList() : edge.getEvents())
                .eventData(edgeWalkDto.getEventData())
                .build());
    }

    @Transactional
    @Override
    public Edge saveEdge(Edge edge) {
        validate(edge);
        return edgeRepository.save(edge);
    }


    @Override
    public List<Edge> findAllEdges() {
        return edgeRepository.findAllEdges();
    }

    @Override
    public Edge findEdgeById(Long id) {
        return edgeRepository.findEdgeById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteEdgeById(Long edgeId) {
        edgeRepository.deleteById(edgeId);
    }


    @Override
    @Transactional
    public Permission savePermission(Permission permission) {
        return permissionChecker.save(permission);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionChecker.findById(id);
    }


    @Override
    public List<Permission> findAllPermissions() {
        return permissionChecker.findAll();
    }

    @Override
    @Transactional
    public void deletePermissionById(Long permissionId) {
        for (Edge edge : edgeRepository.findAllByPermission_Id(permissionId)) {
            deleteEdgeById(edge.getId());
        }
        permissionChecker.deleteById(permissionId);
    }

    @Override
    public Event findEventById(Long id) {
        return eventRunnerService.findById(id);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRunnerService.findAll();
    }

    @Override
    @Transactional
    public Event saveEvent(Event event) {
        validate(event);
        return eventRunnerService.save(event);
    }

    @Override
    @Transactional
    public void deleteEventById(Long eventId) {
        for (Edge edge : edgeRepository.findAllByEvents_Id(eventId)) {
            edge.removeEventFromEdge(findEventById(eventId));
        }
        eventRunnerService.deleteById(eventId);
    }

    protected void runEvents(EventDto eventDto) {
        eventRunnerService.runEvents(eventDto);
    }

    protected void setPermissionCheckerService(PermissionCheckerService permissionCheckerService) {
        this.permissionChecker = permissionCheckerService;
    }

    private void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new CustomBadRequestException(sb.toString());
        }
    }

}
