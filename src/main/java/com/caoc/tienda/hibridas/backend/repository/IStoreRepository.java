package com.caoc.tienda.hibridas.backend.repository;

import com.caoc.tienda.hibridas.backend.repository.entities.StoreEntity;
import org.springframework.data.repository.CrudRepository;

public interface IStoreRepository extends CrudRepository<StoreEntity, Long> {
}
