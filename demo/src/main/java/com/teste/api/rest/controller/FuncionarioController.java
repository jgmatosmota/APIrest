/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teste.api.rest.controller;

import com.teste.api.rest.exception.NotFoundException;
import com.teste.api.rest.model.Funcionario;
import com.teste.api.rest.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

/**
 *
 * @author jgmat
 */
@RestController
@RequestMapping(value = "/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll(){
        List<Funcionario> funcionarios = funcionarioService.findAll();

        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Funcionario> save (@RequestBody Funcionario funcionario){
        Funcionario savedFuncionario = funcionarioService.save(funcionario);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario){
        Optional<Funcionario> funcionarioOptional = funcionarioService.findById(id);
        if(funcionarioOptional.isPresent()){
            funcionario.setId(id);
            Funcionario updatedFuncionario = funcionarioService.save(funcionario);
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioService.findById(id);
        if(funcionarioOptional.isPresent()){
            funcionarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Funcionario> login(@RequestBody Funcionario funcionario){
        Funcionario foundFuncionario = funcionarioService.findByEmailAndPassword(funcionario.getEmail(), funcionario.getSenha());
        if(foundFuncionario != null){
            return new ResponseEntity<>(foundFuncionario, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

