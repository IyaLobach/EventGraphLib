package ru.vitasoft.statusrouteservicelibrary.services.edge;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;
import ru.vitasoft.statusrouteservicelibrary.repository.EdgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.vitasoft.statusrouteservicelibrary.dto.EdgeWalkDto;
import ru.vitasoft.statusrouteservicelibrary.dto.EventDto;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.services.permission.PermissionCheckerService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EdgeWalkerService implements EdgeWalker {


    private final EdgeRepository edgeRepository;

    private final PermissionCheckerService permissionChecker;


    @Override
    public EventDto edgeWalk(@Validated EdgeWalkDto edgeWalkDto) {
        Edge edge = edgeRepository.findByStatusFromIdAndStatusToId(edgeWalkDto.getStatusFromId(), edgeWalkDto.getStatusToId())
                .orElseThrow(() -> new CustomConflictException("Переход из текущего статуса в заданный невозможен"));
        permissionChecker.checkPermission(edge.getPermission(), edgeWalkDto.getCurrentUser(), edgeWalkDto.getOwnerUser());
        return EventDto.builder()
                .eventsId(edge.getEventsId() == null ? Collections.emptyList() : edge.getEventsId())
                .eventData(edgeWalkDto.getEventData())
                .build();
    }

    @Override
    public Edge save(Edge edge) {
        return edgeRepository.save(edge);
    }

    @Override
    public List<Edge> findAllEdges() {
        return edgeRepository.findAll();
    }

    @Override
    public Permission save(Permission permission) {
        return permissionChecker.save(permission);
    }

    @Override
    public Permission findById(Long id) {
        return permissionChecker.findById(id);
    }

    @Override
    public List<Permission> findAllPermissions() {
        return permissionChecker.findAll();
    }
}
