
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.CategoryDTO;
import com.example.Online_Assessment.Entity.Category;
import com.example.Online_Assessment.Mapper.CategoryMapper;
import com.example.Online_Assessment.Repository.CategoryRepository;
import com.example.Online_Assessment.Service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public CategoryDTO createCategory(CategoryDTO dto){
        Category saved=categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDTO(saved);
    }
    
    @Override
    public CategoryDTO getCategory(Long id){
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElse(null);
    }
    
    @Override
    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CategoryDTO updateCategory(Long id,CategoryDTO dto){
        Category existing=categoryRepository.findById(id).orElse(null);
        if(existing == null) return null;
        existing.setName(dto.getName());
        Category updated=categoryRepository.save(existing);
        return categoryMapper.toDTO(updated);
    }
    
    @Override
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
