package com.github.setyset.springtest.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema = "spring_test", name = "price_list")
@Data
public class PriceList {
    @Id
    private String id;

    private String code;
    private String description;
    private Boolean isActive;
}
