package com.example.bootcamp.repos;

import com.example.bootcamp.entities.CategoryMetaData;
import com.example.bootcamp.entities.CategoryMetadataFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMetaDataValueRepository extends JpaRepository<CategoryMetadataFieldValue,Long> {
    CategoryMetadataFieldValue findCategoryMetadataFieldValueByValue(String s);

    CategoryMetadataFieldValue findCategoryMetadataFieldValueById(Long id);


}
