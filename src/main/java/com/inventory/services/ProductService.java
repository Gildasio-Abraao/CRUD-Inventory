package com.inventory.services;

import com.inventory.exceptions.DocumentNotFoundException;
import com.inventory.models.ProductModel;
import com.inventory.repositories.ProductRepository;
import com.inventory.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {
    private final Logger logger = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<ProductModel> findById(String ID) {
        Optional<ProductModel> product = productRepository.findById(ID);

        if(product.isEmpty())
            throw new DocumentNotFoundException("No records found!");

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public ResponseEntity<HttpResponse> save(ProductModel product) {
        productRepository.insert(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new HttpResponse(HttpStatus.CREATED.value(), "Document saved successfully!"));
    }

    @Transactional
    public ResponseEntity<HttpResponse> update(ProductModel product) {
        if(productRepository.findById(product.getId()).isEmpty())
            throw new DocumentNotFoundException("No records found!");

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.OK).body(new HttpResponse(HttpStatus.OK.value(), "Document updated successfully!"));
    }

    @Transactional
    public ResponseEntity<HttpResponse> delete(String id) {
        if(productRepository.findById(id).isEmpty())
            throw new DocumentNotFoundException("No records found!");

        productRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new HttpResponse(HttpStatus.ACCEPTED.value(), "Document deleted successfully!"));
    }
}
