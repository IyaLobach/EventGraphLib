package ru.vitasoft.statusrouteservicelibrary.services.permission;

import org.springframework.transaction.annotation.Transactional;
import ru.vitasoft.statusrouteservicelibrary.dto.EdgeWalkDto;
import ru.vitasoft.statusrouteservicelibrary.exception.CustomConflictException;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;
import ru.vitasoft.statusrouteservicelibrary.model.security.Role;
import ru.vitasoft.statusrouteservicelibrary.model.security.User;
import ru.vitasoft.statusrouteservicelibrary.repository.enums.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@Transactional(readOnly = true)
public abstract class PermissionCheckerService {

    private PermissionRepository permissionRepository;

    public abstract void checkPermission(Permission permission, EdgeWalkDto edgeWalkDto);

    protected void checkRoleAndOwnerPermission(Permission permission, EdgeWalkDto edgeWalkDto) {
        User currentUser = edgeWalkDto.getCurrentUser();
        User ownerUser = edgeWalkDto.getOwnerUser();
        if(permission != null) {
            if(permission.getIsOwner() != null && permission.getIsOwner() && (permission.getRoles() != null && !permission.getRoles().isEmpty())) {
                if (!isOwner(permission, currentUser, ownerUser) && !isHasRole(permission, currentUser)) {
                    throw new CustomConflictException("У пользователя нет прав или он не является владельцем");
                }
            } else {
                if (permission.getIsOwner() != null && permission.getIsOwner()) {
                    if (!isOwner(permission, currentUser, ownerUser)) {
                        throw new CustomConflictException("Пользователь не является владельцем");
                    }
                }
                if (permission.getRoles() != null && !permission.getRoles().isEmpty()) {
                    if (!isHasRole(permission, currentUser)) {
                        throw new CustomConflictException("У пользователя нет прав");
                    }
                }
            }
        }
    }

    private boolean isHasRole(Permission permission, User currentUser) {
        if (!permission.getRoles().isEmpty()) {
            boolean hasRole = permission.getRoles().stream().anyMatch(roleId -> hasAuthority(currentUser, roleId));
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

    @Transactional
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }


    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }


    @Transactional
    public void deleteById(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }


    @Transactional
    public void setPermissionCheckerRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
}
