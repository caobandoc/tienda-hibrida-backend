package com.caoc.tienda.hibridas.backend.repository;

import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByAuthId(String authId);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
