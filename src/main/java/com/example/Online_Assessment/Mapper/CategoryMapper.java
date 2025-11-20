
package com.example.Online_Assessment.Mapper;

import com.example.Online_Assessment.DTO.CategoryDTO;
import com.example.Online_Assessment.Entity.Category;
import org.springframework.stereotype.Component;


@Component
public class CategoryMapper {
    
    public CategoryDTO toDTO(Category category){
        CategoryDTO dto=new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
    
    public Category toEntity(CategoryDTO dto){
        Category category=new Category();
        category.setName(dto.getName());
        return category;
    }
}
