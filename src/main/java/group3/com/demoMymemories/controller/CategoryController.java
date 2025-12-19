package group3.com.demoMymemories.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import group3.com.demoMymemories.entity.Category;
import group3.com.demoMymemories.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
