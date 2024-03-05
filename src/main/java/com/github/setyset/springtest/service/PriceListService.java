package com.github.setyset.springtest.service;

import com.github.setyset.springtest.model.dto.PriceListDTO;

import java.util.List;

public interface PriceListService {
    List<PriceListDTO> getAll();

    PriceListDTO getById(String id);

    Boolean deleteById(String id);

    void updateById(String id, PriceListDTO priceListDTO);

    void create(PriceListDTO priceListDTO);

    String savePriceList(String code, String description);
}
