package org.example.controller;

import dtos.product.AddProductDTO;
import dtos.product.GetProductsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    GetProductsDTO all() {
        return new GetProductsDTO(productService.getProducts());
    }

    @PostMapping("/products")
    void add(@RequestBody AddProductDTO dto) {
        productService.addProduct(dto);
    }
}
