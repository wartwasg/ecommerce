package ecom.api.categoryModule;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String orderBy);

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO deleteCategory(UUID uuid);

    CategoryDTO updateCategory(CategoryDTO category, UUID uuid);
}
