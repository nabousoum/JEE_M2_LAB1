package com.dev.services.dao;

import com.dev.services.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IProductRepository  extends JpaRepository<Product, Integer> {
}
