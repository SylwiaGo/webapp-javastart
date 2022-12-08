package pl.uz.domian.product.dto;

import pl.uz.domian.category.Category;

public class ProductDto {
    private Long id;
    private String name;
    private Float price;
    private String category;
    private String producer;
    private boolean promoted;
    private String shortDescription;
    private String description;
    private String image;

    public ProductDto(Long id, String name, Float price, String category, String producer, boolean promoted, String shortDescription, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.producer = producer;
        this.promoted = promoted;
        this.shortDescription = shortDescription;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
