package ru.vitasoft.statusrouteservicelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;

import java.util.List;
import java.util.Optional;


@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    Optional<Edge> findByStatusFromIdAndStatusToId(Long statusFromId, Long statusToId);

    @Query("SELECT e FROM Edge e LEFT JOIN FETCH e.events WHERE e.id = :id")
    Optional<Edge> findEdgeById(@Param("id") Long id);

    @Query("SELECT e FROM Edge e LEFT JOIN FETCH e.events")
    List<Edge> findAllEdges();

    List<Edge> findAllByEvents_Id(Long id);
    List<Edge> findAllByPermission_Id(Long id);

}
