package group3.com.demoMymemories.service;

import org.springframework.stereotype.Service;
import java.util.List;

import group3.com.demoMymemories.entity.Category;
import group3.com.demoMymemories.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
