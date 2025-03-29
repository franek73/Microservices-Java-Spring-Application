package pl.edu.pg.eti.category.services;

import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.event.repository.EventCategoryRepository;
import pl.edu.pg.eti.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final EventCategoryRepository eventCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, EventCategoryRepository eventCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        eventCategoryRepository.save(category.getUuid());
        return categoryRepository.save(category);
    }

    public Category createOnce(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
        eventCategoryRepository.deleteById(id);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
