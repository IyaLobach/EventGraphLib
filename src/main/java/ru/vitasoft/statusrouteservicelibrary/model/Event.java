package ru.vitasoft.statusrouteservicelibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
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
