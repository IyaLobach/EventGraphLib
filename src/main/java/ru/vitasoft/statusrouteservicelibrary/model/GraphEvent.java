package ru.vitasoft.statusrouteservicelibrary.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GraphEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Название события не может быть null")
    private String type;

    @NotNull(message = "Название бина в событии не может быть null")
    @Column(name = "bean_name")
    private String beanName;

    @NotNull(message = "Метод бина в событии не может быть null")
    @Column(name = "bean_method")
    private String beanMethod;

}
