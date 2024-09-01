package joaovitorffeijo.basic_api.model.product;

import joaovitorffeijo.basic_api.model.supplier.SupplierDTO;

import java.time.LocalDateTime;

public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private SupplierDTO supplier;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.supplier = new SupplierDTO(product.getSupplier());
        this.createdAt = product.getCreatedAt();
        this.modifiedAt = product.getModifiedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
