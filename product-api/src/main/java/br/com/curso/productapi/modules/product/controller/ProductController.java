package br.com.curso.productapi.modules.product.controller;

import br.com.curso.productapi.modules.product.dto.ProductRequest;
import br.com.curso.productapi.modules.product.dto.ProductResponse;
import br.com.curso.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request){
        return productService.save(request);
    }

    @GetMapping
    public List<ProductResponse> findAll(){
        return  productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Integer id){
        return productService.findByIdResponse(id);
    }

    @GetMapping("/name/{name}")
    public List<ProductResponse> findByName(@PathVariable String name){
        return productService.findByName(name);
    }

}
