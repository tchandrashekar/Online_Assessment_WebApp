
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.CategoryDTO;
import com.example.Online_Assessment.Service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/create")
    public CategoryDTO create(@RequestBody CategoryDTO dto){
        return categoryService.createCategory(dto);
    }
    
    @GetMapping("/{id}")
    public CategoryDTO get(@PathVariable Long id){
        return categoryService.getCategory(id);
    }
    
    @GetMapping("/all")
    public List<CategoryDTO> getAll(){
        return categoryService.getAllCategories();
    }
    
    @PutMapping("/update/{id}")
    public CategoryDTO update(@PathVariable Long id,@RequestBody CategoryDTO dto){
        return categoryService.updateCategory(id,dto);
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "Category deleted";
    }
    
}
