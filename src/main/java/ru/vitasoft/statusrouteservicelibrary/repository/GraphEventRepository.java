package ru.vitasoft.statusrouteservicelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.GraphEvent;

@Repository
public interface GraphEventRepository extends JpaRepository<GraphEvent, Long> {

}
