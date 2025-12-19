package group3.com.demoMymemories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import group3.com.demoMymemories.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
