package ecom.api.categoryModule;

import ecom.api.categoryModule.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class CategoryController {

    private CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping("/api/admin/category")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return ResponseEntity.ok("Category added successfully");
    }
    @DeleteMapping("/api/admin/category/{uuid}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID uuid){
        categoryService.deleteCategory(uuid);
        return ResponseEntity.ok("Category was deleted successful");
    }
    @PutMapping("/api/admin/category/{uuid}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable UUID uuid){
        Category updatedCategory = categoryService.updateCategory(category,uuid);
        return ResponseEntity.ok("Successful updated the category to "+updatedCategory);
    }
}
