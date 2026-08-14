package ecom.api.categoryModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category category = categoryOptional
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));

        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Category category, UUID uuid) {

        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category newCategory = categoryOptional
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));
        newCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(newCategory);
        return newCategory;
    }
}
