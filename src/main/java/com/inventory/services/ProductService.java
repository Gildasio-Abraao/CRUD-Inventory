package com.inventory.services;

import com.inventory.exceptions.ArgumentNotValidException;
import com.inventory.exceptions.DocumentNotFoundException;
import com.inventory.exceptions.GenericException;
import com.inventory.models.ProductModel;
import com.inventory.repositories.ProductRepository;
import com.inventory.utils.HttpUtils;
import com.mongodb.MongoBulkWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public ResponseEntity<HashMap<String, String>> save(ProductModel product) {
        try {
            productRepository.insert(product);
        } catch (ArgumentNotValidException exception) {
            logger.warning(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new HttpUtils("Document saved successfully!").setBodyResponse());
    }

    public ResponseEntity<HashMap<String, String>> update(ProductModel product) {
        try {
            productRepository.save(product);
        } catch (ArgumentNotValidException exception) {
            logger.warning(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new HttpUtils("Document updated successfully!").setBodyResponse());
    }

    public ResponseEntity<HashMap<String, String>> delete(String id) {
        try {
            productRepository.deleteById(id);
        } catch (GenericException exception) {
            logger.warning(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(new HttpUtils("Document deleted successfully!").setBodyResponse());
    }
}
