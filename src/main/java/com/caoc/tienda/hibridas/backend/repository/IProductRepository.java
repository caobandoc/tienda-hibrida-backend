package com.caoc.tienda.hibridas.backend.repository;

import com.caoc.tienda.hibridas.backend.repository.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<ProductEntity, Long> {
}
