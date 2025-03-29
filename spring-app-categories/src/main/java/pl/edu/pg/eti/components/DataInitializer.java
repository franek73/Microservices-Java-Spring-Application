package pl.edu.pg.eti.components;

import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
public class DataInitializer {

    private final CategoryService categoryService;

    @Autowired
    public DataInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @PostConstruct
    public void init() {
        Category woodwind = new Category();
        woodwind.setUuid(UUID.fromString("e6a148d1-90a3-4fb3-8f6e-7885cd24e8ae"));
        woodwind.setName("Woodwind");
        woodwind.setDescription("Woodwind");
        categoryService.createOnce(woodwind);

        System.out.println("Initial data has been added");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up resources in DataInitializer...");

        categoryService.deleteAll();

        System.out.println("Cleanup completed");
    }
}
