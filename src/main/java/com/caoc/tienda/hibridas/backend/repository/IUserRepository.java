package com.caoc.tienda.hibridas.backend.repository;

import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    List<UserEntity> findByEmail(String email);
}
