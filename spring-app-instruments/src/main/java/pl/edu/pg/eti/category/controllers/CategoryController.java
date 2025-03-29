package pl.edu.pg.eti.category.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.services.CategoryService;

import java.util.*;

@RestController
@Log
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PutMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putCategory(@PathVariable("id") UUID id) {
        categoryService.findById(id).ifPresentOrElse(
                existingCategory -> categoryService.create(existingCategory),
                () -> categoryService.create(Category.builder().uuid(id).build())
        );
    }

    @DeleteMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") UUID id) {
        categoryService.findById(id)
                .ifPresentOrElse(
                        category -> categoryService.deleteById(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

}
