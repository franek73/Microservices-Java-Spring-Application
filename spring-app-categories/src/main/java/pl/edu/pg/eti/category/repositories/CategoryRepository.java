package pl.edu.pg.eti.category.repositories;

import pl.edu.pg.eti.category.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
