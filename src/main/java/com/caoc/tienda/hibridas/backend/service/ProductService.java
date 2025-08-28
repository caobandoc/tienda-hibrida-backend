package com.caoc.tienda.hibridas.backend.service;

import com.caoc.tienda.hibridas.backend.repository.IProductRepository;
import com.caoc.tienda.hibridas.backend.repository.mapper.ProductMapper;
import com.caoc.tienda.hibridas.backend.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> findAll() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElse(null));
    }
}
