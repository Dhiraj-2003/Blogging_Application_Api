package com.dhiraj.blog.services;

import com.dhiraj.blog.entities.Category;
import com.dhiraj.blog.exceptions.ResourceNotFoundException;
import com.dhiraj.blog.payloads.CategoryDto;
import com.dhiraj.blog.payloads.CategoryResponse;
import com.dhiraj.blog.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepository.save(category);
        return modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",id));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat = categoryRepository.save(cat);
        return modelMapper.map(updatedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category cat = categoryRepository.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",catId));
        categoryRepository.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category cat = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",id));
        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Category> pageCat = categoryRepository.findAll(page);

        List<Category> categories = pageCat.getContent();

        List<CategoryDto> categoryDtos = categories.stream().map((cat)->modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDtos);
        categoryResponse.setPageNumber(pageCat.getNumber());
        categoryResponse.setPageSize(pageCat.getSize());
        categoryResponse.setTotalPages(pageCat.getTotalPages());
        categoryResponse.setTotalElements(pageCat.getNumberOfElements());
        categoryResponse.setLastPage(pageCat.isLast());

        return categoryResponse;
    }
}
