package pl.edu.pg.eti.category.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.pg.eti.category.dtos.GetCategoriesResponse;
import pl.edu.pg.eti.category.dtos.GetCategoryResponse;
import pl.edu.pg.eti.category.dtos.PutCategoryRequest;
import pl.edu.pg.eti.category.entities.Category;
import pl.edu.pg.eti.category.services.CategoryService;

import java.util.*;
import java.util.function.*;

@RestController
@Log
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private final Function<Category, GetCategoryResponse> categoryToResponse = entity -> {
        return GetCategoryResponse.builder()
                .uuid(entity.getUuid())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    };

    private final Function<List<Category>, GetCategoriesResponse> categoriesToResponse = entities -> {
        return GetCategoriesResponse.builder()
                .categories(entities.stream()
                        .map(category -> GetCategoriesResponse.Category.builder()
                                .uuid(category.getUuid())
                                .name(category.getName())
                                .description(category.getDescription())
                                .build())
                        .toList())
                .build();
    };

    private final BiFunction<PutCategoryRequest, UUID, Category> requestToCategory = (request, id) -> {
        return Category.builder()
                .uuid(id)
                .name(request.getName())
                .description(request.getDescription())
                .build();
    };


    @GetMapping("/api/categories")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetCategoriesResponse getCategories() {
        return categoriesToResponse.apply(categoryService.findAll());
    }

    @GetMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetCategoryResponse getCategory(@PathVariable("id") UUID id) {
        return categoryService.findById(id)
                .map(categoryToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void putCategory(@PathVariable("id") UUID id, @RequestBody PutCategoryRequest request) {
        categoryService.findById(id).ifPresentOrElse(
                existingCategory -> {
                    existingCategory.setName(request.getName());
                    existingCategory.setDescription(request.getDescription());

                    categoryService.create(existingCategory);
                },
                () -> categoryService.create(requestToCategory.apply(request, id))
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
