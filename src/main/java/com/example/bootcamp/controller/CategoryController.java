package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CategoryMetaDataDto;
import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.entities.Category;
import com.example.bootcamp.entities.CategoryMetaData;
import com.example.bootcamp.entities.CategoryMetadataFieldValue;
import com.example.bootcamp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addMetadataField")
    public ResponseDTO addMetadataField(@RequestBody CategoryMetaDataDto categoryMetaData){
        if(!categoryService.fieldExist(categoryMetaData.getName())){
            categoryService.addCategoryMeta(categoryMetaData);
            return new  ResponseDTO (HttpStatus.OK,"","Added Successfully");
        }
        else {
            return new ResponseDTO(HttpStatus.BAD_REQUEST,"","Already Exists");
        }
    }

    @GetMapping("/getAllMetadataField")
    public List<CategoryMetaData> getAllMetaData(){
        return categoryService.getAll();
    }

    @PostMapping("/addCategory")
    public ResponseDTO addCategory(@RequestBody Category category){
        if(!categoryService.categoryExist(category.getName())){
            categoryService.addCategory(category);
            return new ResponseDTO(HttpStatus.OK,"","Added Successfully");
        }
        return new ResponseDTO(HttpStatus.BAD_REQUEST,"","Already Exists");
    }

    @GetMapping("/getCategory/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseDTO  updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        if(!categoryService.categoryExist(category.getName())){
            categoryService.updateCategory(category, id);
            return new ResponseDTO( HttpStatus.OK,"","Update Successfully");
        }
        else {
            return new ResponseDTO( HttpStatus.BAD_REQUEST,"","Category with this name already Exists");
        }
    }

    @PostMapping("/mapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}")
    public ResponseDTO addCategoryToCategoryMetaDataFieldValue(@PathVariable Long categoryid, @PathVariable Long categorymetadatafieldid,@RequestBody CategoryMetadataFieldValue categoryMetadataFieldValue){
        if (!categoryService.categoryFieldValueExist(categoryMetadataFieldValue.getValue())){
            categoryService.addCategoryMetaDataToCategory(categoryid, categorymetadatafieldid, categoryMetadataFieldValue);
            return new ResponseDTO( HttpStatus.OK,"","Success");
        }
        else {
            return new ResponseDTO( HttpStatus.BAD_REQUEST,"","Values already Exist");
        }
    }

    @PutMapping("/updateMapCategoryToCategoryMetaDataFieldValue/{categoryid}/{categorymetadatafieldid}")
    public ResponseDTO updateCategoryToCategoryMetaDataFieldValue(@PathVariable Long categoryid, @PathVariable Long categorymetadatafieldid,@RequestBody CategoryMetadataFieldValue categoryMetadataFieldValue){
        if (!categoryService.categoryFieldValueExist(categoryMetadataFieldValue.getValue())){
            categoryService.updateCategoryMetaDataToCategory(categoryid, categorymetadatafieldid, categoryMetadataFieldValue);
            return new  ResponseDTO(HttpStatus.OK,"","Success");
        }
        else {
            return new ResponseDTO( HttpStatus.BAD_REQUEST,"","Values already Exist");
        }
    }


    @GetMapping("/getCategoriesWithChildByCategoryId/{id}")
    public List<Category> getCategoriesWithChildbyId(@PathVariable Long id){
        return categoryService.getChildCategories(id);
    }
}