package ru.vitasoft.statusrouteservicelibrary.services.permission;

import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;
import ru.vitasoft.statusrouteservicelibrary.model.security.Role;
import ru.vitasoft.statusrouteservicelibrary.model.security.User;
import ru.vitasoft.statusrouteservicelibrary.repository.enums.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PermissionCheckerService {

    private final PermissionRepository permissionRepository;

    public void checkPermission(Permission permission, User currentUser, User ownerUser) {
        if(permission != null) {
            if(permission.getIsOwner() != null && permission.getIsOwner() && (permission.getRolesId() != null && !permission.getRolesId().isEmpty())) {
                if (!isOwner(permission, currentUser, ownerUser) && !isHasRole(permission, currentUser)) {
                    throw new CustomConflictException("У пользователя нет прав или он не является владельцем");
                }
            } else {
                if (permission.getIsOwner() != null && permission.getIsOwner()) {
                    if (!isOwner(permission, currentUser, ownerUser)) {
                        throw new CustomConflictException("Пользователь не является владельцем");
                    }
                }
                if (permission.getRolesId() != null && !permission.getRolesId().isEmpty()) {
                    if (!isHasRole(permission, currentUser)) {
                        throw new CustomConflictException("У пользователя нет прав");
                    }
                }
            }
        }
    }

    private boolean isHasRole(Permission permission, User currentUser) {
        if (!permission.getRolesId().isEmpty()) {
            boolean hasRole = permission.getRolesId().stream().anyMatch(roleId -> hasAuthority(currentUser, roleId));
            if (!hasRole)
                return false;
        }
        return true;
    }

    private boolean isOwner(Permission permission, User currentUser, User ownerUser) {
        if (permission.getIsOwner() && (!Objects.equals(ownerUser, currentUser)))
            return false;
        return true;
    }

    private boolean hasAuthority(User user, Long roleId) {
        return user.getRoles()
                .stream()
                .map(Role::getId)
                .anyMatch(s -> s.equals(roleId));
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }



    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }


    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
