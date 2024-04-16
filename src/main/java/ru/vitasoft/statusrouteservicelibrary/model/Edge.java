package ru.vitasoft.statusrouteservicelibrary.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "status_from_id")
    private Long statusFromId;

    @NotNull
    @Column(name = "status_to_id")
    private Long statusToId;

    @ManyToOne
    private Permission permission;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> eventsId;

}
