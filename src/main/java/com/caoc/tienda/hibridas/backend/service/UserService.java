package com.caoc.tienda.hibridas.backend.service;

import com.caoc.tienda.hibridas.backend.exception.EmailUseException;
import com.caoc.tienda.hibridas.backend.repository.IUserRepository;
import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import com.caoc.tienda.hibridas.backend.repository.mapper.UserMapper;
import com.caoc.tienda.hibridas.backend.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto save(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail())
                .stream()
                .findFirst()
                .ifPresent(user -> {
                    throw new EmailUseException("El email ya está en uso");
                });
        UserEntity user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    public UserDto login(UserDto userDto) {
        return userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword())
                .map(userMapper::toDto)
                .orElse(null);
    }
}
