package com.example.bootcamp.repos;

import com.example.bootcamp.entities.CategoryMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMetDataRepository extends JpaRepository<CategoryMetaData,Long> {
    CategoryMetaData findByName(String s);

    CategoryMetaData findCategoryMetaDataById(Long id);
}
