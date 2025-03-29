package pl.edu.pg.eti.category.event.repository;

import java.util.UUID;

public interface EventCategoryRepository {

    void deleteById(UUID id);

    void save(UUID id);
}

