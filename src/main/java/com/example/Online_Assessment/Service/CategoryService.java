
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.CategoryDTO;
import java.util.List;


public interface CategoryService {
    
    CategoryDTO createCategory(CategoryDTO dto);
    CategoryDTO getCategory(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(Long id,CategoryDTO dto);
    void deleteCategory(Long id);
    
}
