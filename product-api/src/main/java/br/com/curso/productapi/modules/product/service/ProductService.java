package br.com.curso.productapi.modules.product.service;

import br.com.curso.productapi.config.exception.ValidationException;
import br.com.curso.productapi.modules.category.service.CategoryService;
import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.product.dto.ProductResponse;
import br.com.curso.productapi.modules.product.model.Product;
import br.com.curso.productapi.modules.product.repository.ProductRepository;
import br.com.curso.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class ProductService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CategoryService categoryService;

    public ProductResponse findByIdResponse(Integer id){
        return ProductResponse.of(findById(id));
    }

    public List<ProductResponse> findAll(){
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name){
        if(isEmpty(name)){
            throw new ValidationException("The product name must be informed.");
        }
        return productRepository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public Product findById(Integer id){
        if(isEmpty(id)){
            throw new ValidationException("The product ID was not informed");
        }
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There's no product for the given ID."));
    }

    public ProductResponse save(ProductRequest request){
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, supplier, category));
        return ProductResponse.of(product);
    }

    private void validateProductDataInformed(ProductRequest request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The product's name was not informed.");
        }
        if(isEmpty(request.getQuantityAvailable())){
            throw new ValidationException("The product's quantity was not informed.");
        }
        if(request.getQuantityAvailable() <=  ZERO){
            throw new ValidationException("The quantity should not to be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierInformed(ProductRequest request){
        if(isEmpty(request.getCategoryId())){
            throw new ValidationException("The category ID was not informed.");
        }
        if(isEmpty(request.getSupplierId())){
            throw new ValidationException("The supplier ID was not informed.");
        }
    }
}
