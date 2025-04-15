package com.fit.productservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    UUID id;

    @Nationalized
    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Nationalized
    @Column(columnDefinition = "text") // PostgreSQL
    String description;

    @ColumnDefault("0")
    @Column(name = "price", precision = 10, scale = 2)
    BigDecimal price = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    Category category;

    @Nationalized
    @Column(name = "thumbnail_url")
    String thumbnailUrl;

    @Column(name = "stock_quantity", nullable = false)
    Integer stockQuantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    @Column(name = "created_at")
    Instant createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    @Column(name = "updated_at")
    Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}