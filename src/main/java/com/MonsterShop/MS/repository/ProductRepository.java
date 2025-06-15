package com.MonsterShop.MS.repository;

import com.MonsterShop.MS.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
