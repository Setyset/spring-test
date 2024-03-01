package com.github.setyset.springtest.model.dto;

import com.github.setyset.springtest.model.entity.PriceList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceListDTO {
    private String id;
    private String code;
    private String description;
    private Boolean isActive;

    public PriceListDTO(PriceList priceList) {
        this.id = priceList.getId();
        this.code = priceList.getCode();
        this.description = priceList.getDescription();
        this.isActive = priceList.getIsActive();
    }
}


