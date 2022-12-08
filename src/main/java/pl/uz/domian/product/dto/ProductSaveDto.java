package pl.uz.domian.product.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductSaveDto {
    private String name;
    private Float price;
    private String category;
    private String producer;
    private boolean promoted;
    private String shortDescription;
    private String description;
    private MultipartFile image;

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public MultipartFile getImage() {
        return image;
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

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
