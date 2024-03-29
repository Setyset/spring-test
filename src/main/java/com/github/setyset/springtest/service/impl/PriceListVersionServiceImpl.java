package com.github.setyset.springtest.service.impl;

import com.github.setyset.springtest.model.dto.PriceListVersionDTO;
import com.github.setyset.springtest.model.entity.PriceList;
import com.github.setyset.springtest.model.entity.PriceListVersion;
import com.github.setyset.springtest.repository.PriceListRepository;
import com.github.setyset.springtest.repository.PriceListVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceListVersionServiceImpl implements com.github.setyset.springtest.service.PriceListVersionService {
    private final PriceListRepository priceListRepository;
    private final PriceListVersionRepository priceListVersionRepository;

    @Override
    public List<PriceListVersionDTO> getAll(){
        return priceListVersionRepository.findAll().stream().map(PriceListVersionDTO::new).toList();    }

    @Override
    public PriceListVersionDTO getById(String id){
        PriceListVersion priceListVersion = priceListVersionRepository.findById(id).orElse(null);
        if (priceListVersion != null){
            return new PriceListVersionDTO(priceListVersion);
        }
        return null;
    }

    @Override
    public Boolean deleteById(String id){
        if (priceListVersionRepository.existsById(id)){
            priceListVersionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void updateById(String id, PriceListVersionDTO priceListVersionDTO){
        PriceListVersion priceListVersion = priceListVersionRepository.findById(id).orElse(null);
        if (priceListVersion == null){
            throw new RuntimeException("Не существует объекта с данным ID");
        }
        priceListVersion.setVersion(priceListVersionDTO.getVersion());
        priceListVersion.setActiveFrom(priceListVersionDTO.getActiveFrom());
        priceListVersion.setActiveTo(priceListVersionDTO.getActiveTo());
        priceListVersion.setIsActive(priceListVersionDTO.getIsActive());
        priceListVersionRepository.save(priceListVersion);
    }

    @Override
    public void create(PriceListVersionDTO priceListVersionDTO) {
        if (priceListVersionDTO.getId() == null || priceListVersionDTO.getId().isEmpty()) {
            throw new RuntimeException("У объекта нет ID");
        }
        if (priceListVersionRepository.existsById(priceListVersionDTO.getId())) {
            throw new RuntimeException("Объект с данным ID существует");
        }

        PriceListVersion priceListVersion = new PriceListVersion();

        priceListVersion.setId(priceListVersionDTO.getId());
        priceListVersion.setActiveFrom(priceListVersionDTO.getActiveFrom());
        priceListVersion.setActiveTo(priceListVersionDTO.getActiveTo());

        PriceList priceList = priceListRepository.findById(priceListVersionDTO.getPriceListId()).orElse(null);
        if (priceList == null) {
            throw new RuntimeException("Price list с данным ID не существует");
        }
        priceListVersion.setPriceList(priceList);
        priceListVersion.setIsActive(priceListVersionDTO.getIsActive());

        priceListVersionRepository.save(priceListVersion);
    }
}
