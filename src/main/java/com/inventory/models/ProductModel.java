package com.inventory.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
public class ProductModel {
    @Id
    private String id;

    @NotBlank(message = "{product.name.blank}")
    @NotNull(message = "{product.name.null}")
    @Length(max = 60, min = 5, message = "{product.name.invalid.char}")
    private String name;

    private String description;

    @NotBlank(message = "{product.category.blank}")
    @NotNull(message = "{product.category.null}")
    @Length(max = 35, min = 5, message = "{product.name.invalid.char}")
    private String category;

    @NotNull(message = "{product.price.null}")
    @Min(value = 0)
    private double price;

    @NotNull(message = "{product.quantity.null}")
    @Min(value = 0)
    private int quantity;

    public ProductModel(String name, String description, String category, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
