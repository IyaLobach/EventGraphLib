package ru.vitasoft.statusrouteservicelibrary.model.security;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode
public class Role {

    private Long id;
    private String name;

    public String getAuthority() {
        return String.format("ROLE_%s", name);
    }
}
