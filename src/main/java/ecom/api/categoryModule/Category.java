package ecom.api.categoryModule;

import ecom.api.productModule.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name="categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    @NotBlank
    @Size(min = 5, message = "Category name must be not less than 5 characters")
    @Column(nullable = false,unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
