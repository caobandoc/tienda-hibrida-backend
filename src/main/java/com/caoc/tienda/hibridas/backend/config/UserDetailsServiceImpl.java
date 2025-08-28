package com.caoc.tienda.hibridas.backend.config;

import com.caoc.tienda.hibridas.backend.exception.EmailNotFoundException;
import com.caoc.tienda.hibridas.backend.repository.IUserRepository;
import com.caoc.tienda.hibridas.backend.repository.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        // 3. Buscar el usuario en la base de datos por su email
        UserEntity user = userRepository.findByEmail(email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new EmailNotFoundException("Correo no encontrado: " + email));

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
