package com.teste.api.rest.service;

import com.teste.api.rest.dto.UsuarioDTO;
import com.teste.api.rest.exception.NotFoundException;
import com.teste.api.rest.model.Usuario;
import com.teste.api.rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id){
        return usuarioRepository.findById(id)
                .map(this::convertToDto);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO){
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDto(savedUsuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO findByEmailAndPassword(String email, String password){
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, password)
                .orElseThrow( ()-> new NotFoundException("Usuario não encontrado"));
        return convertToDto(usuario);
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }

    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }
}

