package ru.vitasoft.statusrouteservicelibrary.model.enums;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> rolesId;

    private Boolean isOwner;

}
