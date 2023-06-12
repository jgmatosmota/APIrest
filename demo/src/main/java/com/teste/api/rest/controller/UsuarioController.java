package com.teste.api.rest.controller;

import com.teste.api.rest.dto.UsuarioDTO;
import com.teste.api.rest.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Retorna todos os usuários.
     * @return ResponseEntity com status 200 e lista de usuários.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<UsuarioDTO> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    /**
     * Salva um novo usuário.
     * @param usuarioDTO DTO do usuário a ser salvo.
     * @return ResponseEntity com status 201 e o usuário salvo.
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> save (@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO savedUsuario = usuarioService.save(usuarioDTO);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    /**
     * Atualiza um usuário.
     * @param id ID do usuário a ser atualizado.
     * @param usuarioDTO DTO do usuário com os dados atualizados.
     * @return ResponseEntity com status 200 e o usuário atualizado ou 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            usuarioDTO.setId(id);
            UsuarioDTO updatedUsuario = usuarioService.save(usuarioDTO);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deleta um usuário.
     * @param id ID do usuário a ser deletado.
     * @return ResponseEntity com status 204 se excluído ou 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<UsuarioDTO> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Autentica um usuário.
     * @param usuarioDTO DTO do usuário a ser autenticado.
     * @return ResponseEntity com status 200 e o usuário autenticado ou 401 se não autenticado.
     */
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO foundUsuario = usuarioService.findByEmailAndPassword(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        if(foundUsuario != null){
            return new ResponseEntity<>(foundUsuario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
