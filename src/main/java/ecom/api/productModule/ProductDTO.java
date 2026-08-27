package ecom.api.productModule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ecom.api.categoryModule.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    @Size(min = 5,max = 100,message = "Invalid description")
    private String description;

    @PositiveOrZero(message = "Invalid discount")
    private Double discount;

    @NotBlank
    private String image;

    @PositiveOrZero(message = "Price should not be less than zero")
    private Double price;

    @NotBlank
    @Size(min = 4,max = 20 ,message = "Invalid product name")
    private String name;

    @PositiveOrZero(message = "Invalid product quantity")
    private Integer quantity;

    private Double special_price;

    @JsonIgnore
    private Category category;
}
