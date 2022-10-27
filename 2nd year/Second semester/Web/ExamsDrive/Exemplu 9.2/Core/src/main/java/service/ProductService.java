package service;

import dto.Product.AddProductDTO;
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

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByInput(String inp) {
        return productRepository.findAllByNameIsStartingWith(inp);
    }

    public void addProduct(AddProductDTO dto) {
        productRepository.save(new Product(dto));
    }

}
