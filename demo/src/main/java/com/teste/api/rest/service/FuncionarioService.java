/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teste.api.rest.service;

import com.teste.api.rest.exception.NotFoundException;
import com.teste.api.rest.model.Funcionario;
import com.teste.api.rest.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author jgmat
 */
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }
    public Optional<Funcionario> findById(Long id){
        return funcionarioRepository.findById(id);
    }
    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
    public void deleteById(Long id){
        funcionarioRepository.deleteById(id);
    }
    public Funcionario findByEmailAndPassword(String email, String password){
        return funcionarioRepository.findByEmailAndSenha(email, password)
                .orElseThrow( ()-> new NotFoundException("Usuario não encontrado"));
    }
}
