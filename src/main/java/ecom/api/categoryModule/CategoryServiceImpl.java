package ecom.api.categoryModule;

import ecom.api.AppConstant;
import ecom.api.error.ApiCustomException;
import ecom.api.error.CategoriesDoesNotExist;
import ecom.api.error.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String orderBy) {

        Sort sortCriteria = orderBy.equalsIgnoreCase("ASC") ?
                 Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortCriteria);

        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty()){
           throw new CategoriesDoesNotExist("Categories does not exist please try again later");
        }
        else{
            List<CategoryDTO> categoryDTOList = categories.stream()
                    .map(category -> modelMapper.map(category,CategoryDTO.class))
                    .toList();
            CategoryResponse categoryResponse = new CategoryResponse();

            categoryResponse.setContent(categoryDTOList);

            categoryResponse.setPageNumber(categoryPage.getNumber());

            categoryResponse.setLastPage(categoryPage.isLast());

            categoryResponse.setTotalElements(categoryPage.getNumberOfElements());

            categoryResponse.setTotalPages(categoryPage.getTotalPages());

            categoryResponse.setPageSize(categoryPage.getSize());

            return categoryResponse;
        }

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category categoryOptional = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryOptional == null){
            Category convertedCategory = modelMapper.map(category,Category.class);
            Category savedCategory = categoryRepository.save(convertedCategory);
            return modelMapper.map(savedCategory,CategoryDTO.class);
        }else{
            throw new ApiCustomException("Category Exists, This category cannot be re-added");
        }
    }

    @Override
    public CategoryDTO deleteCategory(UUID uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category category = categoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("category","CategoryID",uuid,"category","Resource Not found"));

        categoryRepository.delete(category);

        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, UUID uuid) {

        Optional<Category> categoryOptional = categoryRepository.findById(uuid);

        Category newCategory = categoryOptional
                .orElseThrow(()-> new ResourceNotFoundException("category","CategoryID",uuid,category.getCategoryName(),"Resource Not found"));
        newCategory.setCategoryName(category.getCategoryName());
        Category savedCategory = categoryRepository.save(newCategory);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
