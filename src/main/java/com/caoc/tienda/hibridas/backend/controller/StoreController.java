package com.caoc.tienda.hibridas.backend.controller;

import com.caoc.tienda.hibridas.backend.service.StoreService;
import com.caoc.tienda.hibridas.backend.service.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDto>> findAll() {
        log.info("Buscando todas las tiendas");
        List<StoreDto> stores = storeService.findAll();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> findById(@PathVariable Long id) {
        log.info("Buscando tienda con ID: {}", id);
        StoreDto store = storeService.findById(id);
        if (store != null) {
            return ResponseEntity.ok(store);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
