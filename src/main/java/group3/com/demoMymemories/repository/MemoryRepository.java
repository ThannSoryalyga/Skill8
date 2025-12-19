package group3.com.demoMymemories.repository;

import group3.com.demoMymemories.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    // You can add custom queries here if needed
}
