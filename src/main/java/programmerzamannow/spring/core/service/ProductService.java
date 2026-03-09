package programmerzamannow.spring.core.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.repository.ProductRepository;

@Component
public class ProductService {

    @Getter
    private ProductRepository productRepository;


    // Autowired ini dikhususkan ketika khasusnya lebih dari 1 construct
    // Karena dia butuh 1 parameter untuk sebagai nilai default maka dari itu tidak boleh lebih dari 1 param
    // Maka dari itu pakai Autowired sebagai penanda bahwa construc ini yang akan dipakai (1 param)
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public ProductService(ProductRepository productRepository, String name){
        this.productRepository = productRepository;
    }
}
