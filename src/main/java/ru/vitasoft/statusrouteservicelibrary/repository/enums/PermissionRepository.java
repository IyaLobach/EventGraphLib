package ru.vitasoft.statusrouteservicelibrary.repository.enums;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
