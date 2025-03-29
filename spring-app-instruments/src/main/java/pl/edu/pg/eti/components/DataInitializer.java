package pl.edu.pg.eti.components;

import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.services.CategoryService;
import pl.edu.pg.eti.instrument.entities.Instrument;
import pl.edu.pg.eti.instrument.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
public class DataInitializer {

    private final CategoryService categoryService;
    private final InstrumentService instrumentService;

    @Autowired
    public DataInitializer(CategoryService categoryService, InstrumentService instrumentService) {
        this.categoryService = categoryService;
        this.instrumentService = instrumentService;
    }

    @PostConstruct
    public void init() {
        Category woodwind = new Category();
        woodwind.setUuid(UUID.fromString("e6a148d1-90a3-4fb3-8f6e-7885cd24e8ae"));
        categoryService.create(woodwind);

        Instrument saxophone = new Instrument();
        saxophone.setUuid(UUID.fromString("d13b6b91-cb61-4ae6-b8b0-c86a7c8d515f"));
        saxophone.setName("Saxophone");
        saxophone.setDescription("Saxophone");
        saxophone.setCategory(woodwind);
        instrumentService.create(saxophone);

        Instrument flute = new Instrument();
        flute.setUuid(UUID.fromString("b23f9dbe-033f-4cc6-b7a5-e06b72a9e7b7"));
        flute.setName("Flute");
        flute.setDescription("Flute");
        flute.setCategory(woodwind);
        instrumentService.create(flute);

        System.out.println("Initial data has been added");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up resources in DataInitializer...");

        instrumentService.deleteAll();

        categoryService.deleteAll();

        System.out.println("Cleanup completed");
    }
}
