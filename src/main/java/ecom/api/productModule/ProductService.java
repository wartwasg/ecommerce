package ecom.api.productModule;

import ecom.api.categoryModule.Category;

import java.util.UUID;

public interface ProductService {
    ProductDTO addProduct(UUID categoryID,ProductDTO productDTO);

    ProductResponse getAllProducts();

    ProductResponse getProductByCategory(UUID categoryId,Integer pageNumber,Integer pageSize,String sortBy,String orderBy);

}
