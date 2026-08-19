package ecom.api.categoryModule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {


    Category findByCategoryName(@NotBlank @Size(min = 5, message = "Category name must be not less than 5 characters") String categoryName);
}
