package com.github.setyset.springtest.service.impl;

import com.github.setyset.springtest.model.dto.PriceListDTO;
import com.github.setyset.springtest.model.entity.PriceList;
import com.github.setyset.springtest.repository.PriceListRepository;
import com.github.setyset.springtest.repository.PriceListVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceListServiceImpl implements com.github.setyset.springtest.service.PriceListService {
    private final PriceListRepository priceListRepository;
    private final PriceListVersionRepository priceListVersionRepository;

    @Override
    public  List<PriceListDTO> getAll(){
        return priceListRepository.findAll().stream().map(PriceListDTO::new).toList();    }

    @Override
    public PriceListDTO getById(String id) {
        PriceList priceList = priceListRepository.findById(id).orElse(null);
        if (priceList != null) {
            return new PriceListDTO(priceList);
        }
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        if (priceListRepository.existsById(id)) {
            priceListRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateById(String id, PriceListDTO priceListDTO){
        PriceList priceList = priceListRepository.findById(id).orElse(null);
        if (priceList == null){
            throw new RuntimeException("Не существует объекта с данным ID");
        }
        priceList.setCode(priceListDTO.getCode());
        priceList.setDescription(priceListDTO.getDescription());
        priceList.setIsActive(priceListDTO.getIsActive());
        priceListRepository.save(priceList);
    }

    @Override
    public void create(PriceListDTO priceListDTO) {
        if (priceListDTO.getId() == null || priceListDTO.getId().isEmpty()) {
            throw new RuntimeException("У объекта нет ID");
        }
        if (priceListRepository.existsById(priceListDTO.getId())) {
            throw new RuntimeException("Объект с данным ID существует");
        }

        PriceList priceList = new PriceList();

        priceList.setId(priceListDTO.getId());
        priceList.setCode(priceListDTO.getCode());
        priceList.setDescription(priceListDTO.getDescription());
        priceList.setIsActive(priceListDTO.getIsActive());

        priceListRepository.save(priceList);
    }

    @Override
    public String savePriceList(String code, String description) {
        PriceList priceList = priceListRepository.findById(code).orElse(null);
        Boolean isActive = false;
        if (priceList != null) {
            if (priceListVersionRepository.existsByPriceListAndIsActiveIsTrue(priceList)) {
                isActive = true;
            }
        } else {
            isActive = true;
        }
        if (priceList == null) {
            priceList = new PriceList();
            priceList.setId(code);
        }

        priceList.setCode(code);
        priceList.setDescription(description);
        priceList.setIsActive(isActive);

        priceListRepository.saveAndFlush(priceList);
        return priceList.getId();
    }
}
