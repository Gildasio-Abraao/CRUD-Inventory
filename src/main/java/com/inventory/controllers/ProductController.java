package com.inventory.controllers;

import com.inventory.models.ProductModel;
import com.inventory.services.ProductService;
import com.inventory.utils.HttpResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductModel> findAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable("id") String ID) {
        return productService.findById(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid ProductModel product) {
        return productService.save(product);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> update(@RequestBody @Valid ProductModel product) {
        return productService.update(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable("id") String id) {
        return productService.delete(id);
    }
}
