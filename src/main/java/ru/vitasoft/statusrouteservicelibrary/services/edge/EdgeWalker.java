package ru.vitasoft.statusrouteservicelibrary.services.edge;


import ru.vitasoft.statusrouteservicelibrary.dto.EdgeWalkDto;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.model.GraphEvent;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;

import java.util.List;

public interface EdgeWalker {

    void edgeWalk(EdgeWalkDto edgeWalkDto);

    Edge saveEdge(Edge edge);

    List<Edge> findAllEdges();

    Edge findEdgeById(Long id);

    void deleteEdgeById(Long edgeId);

    Permission savePermission(Permission permission);

    Permission findPermissionById(Long id);

    List<Permission> findAllPermissions();
    void deletePermissionById(Long permissionId);

    GraphEvent findEventById(Long id);

    List<GraphEvent> findAllEvents();

    GraphEvent saveEvent(GraphEvent event);

    void deleteEventById(Long eventId);

    Edge addEvent(Long edgeId, GraphEvent graphEvent);

}
