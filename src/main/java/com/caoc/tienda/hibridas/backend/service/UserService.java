package com.caoc.tienda.hibridas.backend.service;

import com.caoc.tienda.hibridas.backend.exception.EmailNotFoundException;
import com.caoc.tienda.hibridas.backend.exception.EmailUseException;
import com.caoc.tienda.hibridas.backend.repository.IUserRepository;
import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import com.caoc.tienda.hibridas.backend.repository.mapper.UserMapper;
import com.caoc.tienda.hibridas.backend.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto save(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail())
                .stream()
                .findFirst()
                .ifPresent(user -> {
                    throw new EmailUseException("El email ya está en uso");
                });
        UserEntity toSave = userMapper.toEntity(userDto);
        toSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity user = userRepository.save(toSave);
        return userMapper.toDto(user);
    }

    public void findByEmail(String email) {
        userRepository.findByEmail(email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new EmailNotFoundException("El correo no está registrado"));
    }
}
