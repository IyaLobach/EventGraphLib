package ru.vitasoft.statusrouteservicelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;

import java.util.Optional;


@Repository
public interface EdgeRepository extends JpaRepository<Edge, Long> {
    Optional<Edge> findByStatusFromIdAndStatusToId(Long statusFromId, Long statusToId);
}
