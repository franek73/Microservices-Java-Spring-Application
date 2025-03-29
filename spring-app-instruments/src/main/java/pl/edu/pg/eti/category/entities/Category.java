package pl.edu.pg.eti.category.entities;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.pg.eti.instrument.entities.Instrument;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @Column(name = "uuid", unique = true, nullable = false)
    private UUID uuid;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Instrument> instruments = new ArrayList<>();

    private static class ProfessionBuilder {
        List<Instrument> instruments = new ArrayList<>();
    }
}
