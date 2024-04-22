package ru.vitasoft.statusrouteservicelibrary.model.enums;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ru.vitasoft.statusrouteservicelibrary.model.Edge;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> roles;

    private Boolean isOwner;
}
