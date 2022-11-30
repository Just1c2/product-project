package com.tass.productservice.controllers;

import com.tass.productservice.model.request.ProductRequest;
import com.tass.productservice.model.response.BaseResponse;
import com.tass.productservice.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping(path = "/product")
public class ProductController extends BaseController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Optional<ProductRequest> optionalProductRequest = productService.findProductByBarcode(productRequest.getBarcode());
            if (optionalProductRequest.isPresent()) {
                log.info("Lỗi trùng barcode.");
                return createdResponse(new BaseResponse(1000, ""));
            }
            if (productRequest.getName().isEmpty() || productRequest.getName() == null) {
                log.info("Lỗi thiếu tên sản phẩm.");
                return createdResponse(new BaseResponse(100, "Tên sản phẩm không được bỏ trống"));
            }
            if (productRequest.getImage().isEmpty() || productRequest.getImage() == null) {
                log.info("Lỗi thiếu ảnh sản phẩm.");
                return createdResponse(new BaseResponse(101, "Ảnh sản phẩm không được bỏ trống"));
            }
            if (productRequest.getContent().isEmpty() || productRequest.getContent() == null) {
                log.info("Lỗi thiếu nội dung sản phẩm.");
                return createdResponse(new BaseResponse(101, "Content sản phẩm không được bỏ trống"));
            }
            if (productRequest.getDescription().isEmpty() || productRequest.getDescription() == null) {
                log.info("Lỗi thiếu mô tả sản phẩm.");
                return createdResponse(new BaseResponse(101, "Miêu tả sản phẩm không được bỏ trống"));
            }
            productService.saveProduct(productRequest);
            return createdResponse(productService.createdProduct(productRequest));
        } catch (Exception e) {
            log.info("Lỗi hệ thống.");
            return createdResponse(new BaseResponse(500, "Hệ thống đang quá tải, xin vui lòng thử lại sau"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}