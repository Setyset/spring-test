package com.github.setyset.springtest.controller;

import com.github.setyset.springtest.model.dto.PriceListVersionDTO;
import com.github.setyset.springtest.service.PriceListVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriceListVersionController {
    private final PriceListVersionService priceListVersionService;

    @GetMapping("/pricelistversion")
    List<PriceListVersionDTO> getAll(){ return priceListVersionService.getAll(); }

    @GetMapping("/pricelistversion/{id}")
    PriceListVersionDTO getById(@PathVariable String id) { return priceListVersionService.getById(id); }

    @DeleteMapping("/pricelistversion/{id}")
    Boolean deleteById(@PathVariable String id) { return priceListVersionService.deleteById(id); }

    @PutMapping("/pricelistversion/{id}")
    void updateById(@PathVariable String id, @RequestBody PriceListVersionDTO priceListVersionDTO){
        priceListVersionService.updateById(id, priceListVersionDTO);
    }
    
    @PostMapping("/pricelistversion")
    void create(@RequestBody PriceListVersionDTO priceListVersionDTO){ priceListVersionService.create(priceListVersionDTO);}
}
