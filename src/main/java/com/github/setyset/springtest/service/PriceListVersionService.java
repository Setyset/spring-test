package com.github.setyset.springtest.service;

import com.github.setyset.springtest.model.dto.PriceListVersionDTO;

import java.util.List;

public interface PriceListVersionService {
    List<PriceListVersionDTO> getAll();

    PriceListVersionDTO getById(String id);

    Boolean deleteById(String id);

    void updateById(String id, PriceListVersionDTO priceListVersionDTO);

    void create(PriceListVersionDTO priceListVersionDTO);
}
