package pl.edu.pg.eti.instrument.repositories;

import pl.edu.pg.eti.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.instrument.entities.Instrument;

import java.util.*;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {

    List<Instrument> findAllByCategory(Category category);
}

