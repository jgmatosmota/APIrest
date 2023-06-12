package com.teste.api.rest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioDTO {
    private Long id;
    private String email;
    private String senha;
}

