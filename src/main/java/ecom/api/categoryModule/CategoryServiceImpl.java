package ecom.api.categoryModule;

import ecom.api.error.ApiCustomException;
import ecom.api.error.CategoriesDoesNotExist;
import ecom.api.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
           throw new CategoriesDoesNotExist("Categories does not exist please try again later");
        }
        else{
            return categoryRepository.findAll();
        }

    }

    @Override
    public void createCategory(Category category) {
        Category categoryOptional = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryOptional == null){
            categoryRepository.save(category);
        }else{
            throw new ApiCustomException("Category Exists, This category cannot be re-added");
        }
    }

    @Override
    public void deleteCategory(UUID uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category category = categoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("category","CategoryID",uuid,"category","Resource Not found"));

        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(Category category, UUID uuid) {

        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category newCategory = categoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("category","CategoryID",uuid,category.getCategoryName(),"Resource Not found"));
        newCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(newCategory);
        return newCategory;
    }
}
