package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRespository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    Category findCategoryById(Long id);

    List<Category> findCategoriesByCategoryId(Long id);


}
