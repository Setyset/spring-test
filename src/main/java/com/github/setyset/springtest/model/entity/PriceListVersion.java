package com.github.setyset.springtest.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(schema = "spring_test", name = "price_list_version")
@Data
public class PriceListVersion {
    @Id
    private String id;

    private Integer version;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate activeFrom;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate activeTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_list")
    private PriceList priceList;

    private Boolean isActive;
}
