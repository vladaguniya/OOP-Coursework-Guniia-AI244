package ua.opnu.labwork2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.opnu.labwork2.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}