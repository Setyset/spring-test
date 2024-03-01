package com.github.setyset.springtest.model.dto;

import com.github.setyset.springtest.model.entity.PriceList;
import com.github.setyset.springtest.model.entity.PriceListVersion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PriceListVersionDTO {
    private String id;
    private Integer version;
    private LocalDate activeFrom;
    private LocalDate activeTo;
    private String priceListId;
    private Boolean isActive;

    public PriceListVersionDTO(PriceListVersion priceListVersion){
        this.id = priceListVersion.getId();
        this.version = priceListVersion.getVersion();
        this.activeFrom = priceListVersion.getActiveFrom();
        this.activeTo = priceListVersion.getActiveTo();
        this.priceListId = priceListVersion.getPriceList() != null ? priceListVersion.getId() : null;
        this.isActive = priceListVersion.getIsActive();
    }
}
