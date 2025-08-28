package com.caoc.tienda.hibridas.backend.service;

import com.caoc.tienda.hibridas.backend.repository.IStoreRepository;
import com.caoc.tienda.hibridas.backend.repository.mapper.StoreMapper;
import com.caoc.tienda.hibridas.backend.service.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final IStoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public List<StoreDto> findAll() {
        return storeMapper.toDtoList(storeRepository.findAll());
    }

    public StoreDto findById(Long id) {
        return storeMapper.toDto(storeRepository.findById(id).orElse(null));
    }
}
