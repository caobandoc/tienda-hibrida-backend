package com.caoc.tienda.hibridas.backend.controller;

import com.caoc.tienda.hibridas.backend.config.JwtService;
import com.caoc.tienda.hibridas.backend.controller.dto.AuthRequest;
import com.caoc.tienda.hibridas.backend.controller.dto.AuthResponse;
import com.caoc.tienda.hibridas.backend.service.UserService;
import com.caoc.tienda.hibridas.backend.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Guardando usuario: {}", userDto);
        UserDto savedUser = userService.save(userDto);
        if (savedUser != null) {
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) {
        // 1. Utilizar el AuthenticationManager para autenticar al usuario
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        // 5. Generar el token JWT si la autenticación es exitosa
        String token = jwtService.generateToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponse(token, jwtService.getExpirationInstant()));
    }
}
