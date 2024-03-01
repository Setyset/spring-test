package com.github.setyset.springtest.controller;

import com.github.setyset.springtest.model.dto.PriceListDTO;
import com.github.setyset.springtest.model.dto.SavePriceListRequestDTO;
import com.github.setyset.springtest.repository.PriceListRepository;
import com.github.setyset.springtest.service.PriceListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriceListController {
    private final PriceListService priceListService;

    @GetMapping("/pricelist")
    List<PriceListDTO> getAll(){
        return priceListService.getAll();
    }

    @GetMapping("/pricelist/{id}")
    PriceListDTO getById(@PathVariable String id){
        return priceListService.getById(id);
    }

    @DeleteMapping("/pricelist/{id}")
    Boolean deleteById(@PathVariable String id){
        return priceListService.deleteById(id);
    }

    @PutMapping("/pricelist/{id}")
    void updateById(@PathVariable String id, @RequestBody PriceListDTO priceListDTO){
        priceListService.updateById(id, priceListDTO);
    }

    @PostMapping("/pricelist")
    void create(@RequestBody PriceListDTO priceListDTO){
        priceListService.create(priceListDTO);
    }

    @PostMapping("/pricelist/customsave")
    String savePriceList(@RequestBody SavePriceListRequestDTO request) {
        return priceListService.savePriceList(request.getCode(), request.getDescription());
    }
}
