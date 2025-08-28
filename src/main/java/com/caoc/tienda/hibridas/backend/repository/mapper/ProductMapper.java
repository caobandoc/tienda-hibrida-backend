package com.caoc.tienda.hibridas.backend.repository.mapper;

import com.caoc.tienda.hibridas.backend.repository.entities.ProductEntity;
import com.caoc.tienda.hibridas.backend.service.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public List<ProductDto> toDtoList(Iterable<ProductEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        List<ProductDto> dtos = new ArrayList<>();
        for (ProductEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public ProductDto toDto(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .urlImage(entity.getUrlImage())
                .storeId(entity.getStore().getId())
                .build();
    }
}
