package com.inventory.services;

import com.inventory.models.ProductModel;
import com.inventory.repositories.ProductRepository;
import com.inventory.utils.HttpUtils;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductService {
    private Logger logger = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public ResponseEntity<HashMap<String, String>> save(ProductModel product) {
        try {
            productRepository.insert(product);
        } catch (MongoBulkWriteException exception) {
            logger.warning(exception.getMessage());
        }

        HttpUtils httpUtils = new HttpUtils("Document saved successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(httpUtils.getResponse());
    }

    public ResponseEntity<HashMap<String, String>> update(ProductModel product) {
        try {
            productRepository.save(product);
        } catch (MongoBulkWriteException exception) {
            logger.warning(exception.getMessage());
        }

        HttpUtils httpUtils = new HttpUtils("Document updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(httpUtils.getResponse());
    }

    public ResponseEntity<HashMap<String, String>> delete(String id) {
        try {
            productRepository.deleteById(id);
        } catch (MongoCommandException exception) {
            logger.warning(exception.getMessage());
        }

        HttpUtils httpUtils = new HttpUtils("Document deleted successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(httpUtils.getResponse());
    }
}
