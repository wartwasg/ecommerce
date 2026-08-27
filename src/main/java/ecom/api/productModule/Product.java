package ecom.api.productModule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecom.api.categoryModule.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String description;

    private double discount;

    private String image;

    private double price;

    @Column(unique = true)
    private String name;

    private Integer quantity;

    private double special_price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
}
