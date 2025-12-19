package group3.com.demoMymemories.Seed;  // ✅ only one package at top

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import group3.com.demoMymemories.entity.Category;      
import group3.com.demoMymemories.repository.CategoryRepository; 

@Component
public class SeedCategory implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public  SeedCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count() == 0) {

            categoryRepository.save(Category.builder().name("Work").build());
            categoryRepository.save(Category.builder().name("Study").build());
            categoryRepository.save(Category.builder().name("Life").build());
            categoryRepository.save(Category.builder().name("Family").build());
            categoryRepository.save(Category.builder().name("Travel").build());

            System.out.println("Category seeded successfully!");
        }
    }
}
