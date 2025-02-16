package com.dev.services.dao;

import com.dev.services.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository  extends JpaRepository<Product, Integer> {
}
