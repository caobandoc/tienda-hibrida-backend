package com.caoc.tienda.hibridas.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StoreDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
}
