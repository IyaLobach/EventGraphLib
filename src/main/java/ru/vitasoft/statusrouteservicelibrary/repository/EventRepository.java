package ru.vitasoft.statusrouteservicelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
