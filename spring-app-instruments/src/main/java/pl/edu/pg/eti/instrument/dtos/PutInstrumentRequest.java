package pl.edu.pg.eti.instrument.dtos;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutInstrumentRequest {
    private String name;

    private String description;

    private UUID category;
}

