package ecom.api.productModule;

import ecom.api.AppConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/api/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@PathVariable UUID categoryId,@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.addProduct(categoryId,productDTO), HttpStatus.CREATED);
    }
    @GetMapping("/api/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @GetMapping("/api/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable UUID categoryId,
                                                                 @RequestParam(name = "page",defaultValue = AppConstant.PAGE_NUMBER, required = false)  Integer pageNumber,
                                                                 @RequestParam(name = "size",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                                 @RequestParam(name = "sort",defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
                                                                 @RequestParam(name = "order",defaultValue = AppConstant.ORDER_BY, required = false) String orderBy){
        return new ResponseEntity<>(productService.getProductByCategory(categoryId,pageNumber,pageSize,sortBy,orderBy),HttpStatus.OK);
    }
}
