package com.caoc.tienda.hibridas.backend.repository.mapper;

import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import com.caoc.tienda.hibridas.backend.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto toDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
