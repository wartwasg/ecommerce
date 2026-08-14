package ecom.api.categoryModule;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();

    void createCategory(Category category);

    void deleteCategory(UUID uuid);

    Category updateCategory(Category category, UUID uuid);
}
