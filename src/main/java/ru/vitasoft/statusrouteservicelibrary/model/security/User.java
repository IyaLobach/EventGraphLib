package ru.vitasoft.statusrouteservicelibrary.model.security;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;


@Data
@EqualsAndHashCode
public class User {
    private Long id;
    private Collection<Role> roles;
}
