package com.dhiraj.blog.services;

import com.dhiraj.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //Create
    CategoryDto createCategory(CategoryDto categoryDto);
    //Update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    //Delete
    void deleteCategory(Integer catId);
    //Get
    CategoryDto getCategory(Integer id);
    //Get All
    List<CategoryDto> getAllCategories();

}
