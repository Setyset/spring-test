package com.github.setyset.springtest.repository;

import com.github.setyset.springtest.model.entity.PriceList;
import com.github.setyset.springtest.model.entity.PriceListVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceListVersionRepository extends JpaRepository<PriceListVersion, String> {
    Boolean existsByPriceListAndIsActiveIsTrue(PriceList priceList);
}
