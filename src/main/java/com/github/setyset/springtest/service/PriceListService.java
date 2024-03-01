package com.github.setyset.springtest.service;

import com.github.setyset.springtest.model.dto.PriceListDTO;
import com.github.setyset.springtest.model.entity.PriceList;
import com.github.setyset.springtest.repository.PriceListRepository;
import com.github.setyset.springtest.repository.PriceListVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceListService {
    private final PriceListRepository priceListRepository;
    private final PriceListVersionRepository priceListVersionRepository;

    public List<PriceListDTO> getAll() {
        List<PriceListDTO> priceListDTOS = new ArrayList<>();
        for (PriceList priceList : priceListRepository.findAll()) {
            priceListDTOS.add(new PriceListDTO(priceList));
        }
        return priceListDTOS;
    }

    public PriceListDTO getById(String id) {
        PriceList priceList = priceListRepository.findById(id).orElse(null);
        if (priceList != null) {
            return new PriceListDTO(priceList);
        }
        return null;
    }

    public Boolean deleteById(String id) {
        if (priceListRepository.existsById(id)) {
            priceListRepository.deleteById(id);
            return true;
        }
        return false;
    }

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
