package com.alexey.GrassCutterShopWithSpring.repo;

import com.alexey.GrassCutterShopWithSpring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository <Order, Long> {

    List<Order> findByUserId(long user_id);
}
