package ru.vitasoft.statusrouteservicelibrary.repository.enums;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;

import javax.persistence.Entity;


@Repository
public interface PermissionRepository<T extends Permission > extends JpaRepository<T, Long> {
}
