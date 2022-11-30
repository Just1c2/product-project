package com.tass.productservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tass.productservice.model.request.ProductRequest;

@Repository
public interface ProductRepo extends JpaRepository<ProductRequest, Integer> {
    Optional<ProductRequest> findProductByBarcode(String barcode);
}
