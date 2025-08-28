package com.caoc.tienda.hibridas.backend.controller.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
