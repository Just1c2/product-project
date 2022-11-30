package com.tass.productservice.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tass.productservice.model.request.ProductRequest;
import com.tass.productservice.model.response.BaseResponse;
import com.tass.productservice.repo.ProductRepo;




@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    
    public BaseResponse createdProduct(ProductRequest request){
        return new BaseResponse();
    }

    public Optional<ProductRequest> findProductByBarcode(String barcode){
        return productRepo.findProductByBarcode(barcode);
    }

    public ProductRequest saveProduct(ProductRequest request) {
        return productRepo.save(request);
    }
}
