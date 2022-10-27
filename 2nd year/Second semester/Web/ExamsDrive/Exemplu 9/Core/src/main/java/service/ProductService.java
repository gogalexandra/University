package service;

import dtos.product.AddProductDTO;
import entities.Product;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void addProduct(AddProductDTO dto){
        this.productRepository.save(new Product(dto));
    }

    public List<Product> getProducts(){return this.productRepository.findAll();}
}
