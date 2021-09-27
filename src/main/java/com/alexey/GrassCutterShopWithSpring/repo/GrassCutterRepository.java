package com.alexey.GrassCutterShopWithSpring.repo;

import com.alexey.GrassCutterShopWithSpring.model.GrassCutter;
import com.alexey.GrassCutterShopWithSpring.model.GrassCutterBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrassCutterRepository extends JpaRepository <GrassCutter, Long> {
    List<GrassCutter> findAllByBrand(GrassCutterBrand brand);
    List<GrassCutter>  findAllByOrderByPrice();
    List<GrassCutter>  findAllByOrderByPriceDesc();

}
