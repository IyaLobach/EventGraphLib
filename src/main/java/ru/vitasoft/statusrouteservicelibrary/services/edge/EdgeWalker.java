package ru.vitasoft.statusrouteservicelibrary.services.edge;


import ru.vitasoft.statusrouteservicelibrary.dto.EdgeWalkDto;
import ru.vitasoft.statusrouteservicelibrary.dto.EventDto;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;

import java.util.List;

public interface EdgeWalker {
   EventDto edgeWalk(EdgeWalkDto edgeWalkDto);
   Edge save(Edge edge);
   List<Edge> findAllEdges();

   Permission save(Permission permission);
   Permission findById(Long id);
   List<Permission> findAllPermissions();

}
