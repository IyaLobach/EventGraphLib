package ru.vitasoft.statusrouteservicelibrary.model;

import lombok.*;
import ru.vitasoft.statusrouteservicelibrary.model.enums.Permission;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"status_from_id", "status_to_id"})})
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Текущий статус должен быть отличен от null")
    @Column(name = "status_from_id")
    private Long statusFromId;

    @NotNull(message = "Статус для перехода должен быть отличен от null")
    @Column(name = "status_to_id")
    private Long statusToId;

    @ManyToOne
    private Permission permission;


    @ManyToMany
    @JoinTable(
            name = "edge_graphevent",
            joinColumns = @JoinColumn(name = "edge_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<GraphEvent> events;

    public void removeEventFromEdge(GraphEvent event) {
        events.remove(event);
    }

}
