package pl.edu.pg.eti.instrument.entities;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.pg.eti.category.entities.Category;

import java.io.Serializable;
import java.util.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode(exclude = "category")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "instruments")
public class Instrument implements Comparable<Instrument>, Serializable {
    @Id
    @Column(name = "uuid", unique = true, nullable = false)
    private UUID uuid;

    @Column(name = "instrument_name", nullable = false)
    private String name;

    @Column(name = "instrument_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Override
    public int compareTo(Instrument o) {
        return name.compareTo(o.name);
    }

}
