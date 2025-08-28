package com.caoc.tienda.hibridas.backend.repository.mapper;

import com.caoc.tienda.hibridas.backend.repository.entities.StoreEntity;
import com.caoc.tienda.hibridas.backend.service.dto.StoreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreMapper {

    public List<StoreDto> toDtoList(Iterable<StoreEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        List<StoreDto> dtoList = new ArrayList<>();
        for (StoreEntity entity : entities) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    public StoreDto toDto(StoreEntity entity) {
        if (entity == null) {
            return null;
        }

        return StoreDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .address(entity.getAddress())
                .build();
    }

}
