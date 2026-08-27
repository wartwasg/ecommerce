package ecom.api.productModule;

import ecom.api.categoryModule.Category;
import ecom.api.categoryModule.CategoryRepository;
import ecom.api.error.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(UUID categoryID, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryID,"Category does not exists"));
        productDTO.setSpecial_price(productDTO.getPrice()-((productDTO.getDiscount()*0.01)*productDTO.getPrice()));
        productDTO.setCategory(category);
        Product product = modelMapper.map(productDTO,Product.class);
        productRepository.save(product);
        return productDTO;
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDTO> productsFromDb = products.stream()
                .map((product -> modelMapper.map(product, ProductDTO.class)))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContents(productsFromDb);
        return productResponse;
    }

    @Override
    public ProductResponse getProductByCategory(UUID categoryId, Integer pageNumber, Integer pageSize, String sortBy, String orderBy) {
        Sort sort =orderBy.equals("ASC")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page = productRepository.findAll(pageDetails);

        List<Product> products = page.getContent();

        List<ProductDTO> productDTOSList = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();


        productResponse.setContents(productDTOSList);
        
        return productResponse;
    }
}
