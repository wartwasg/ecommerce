package ecom.api.categoryModule;

import ecom.api.AppConstant;
import ecom.api.categoryModule.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
                                                             @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
                                                             @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
                                                             @RequestParam(name = "ORDER_BY",defaultValue = AppConstant.ORDER_BY,required = false) String orderBy){
        return ResponseEntity.ok(categoryService.getAllCategories(pageNumber,pageSize,sortBy,orderBy));
    }
    @PostMapping("/api/admin/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        CategoryDTO savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }
    @DeleteMapping("/api/admin/category/{uuid}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable UUID uuid){
        CategoryDTO deletedCategory = categoryService.deleteCategory(uuid);
        return new ResponseEntity<>(deletedCategory,HttpStatus.OK);
    }
    @PutMapping("/api/admin/category/{uuid}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO category, @PathVariable UUID uuid){
        CategoryDTO updatedCategory = categoryService.updateCategory(category,uuid);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
}
