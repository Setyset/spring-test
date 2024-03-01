package com.github.setyset.springtest.repository;

import com.github.setyset.springtest.model.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, String> {
}
