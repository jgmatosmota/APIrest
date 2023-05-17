/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.teste.api.rest.repository;

import com.teste.api.rest.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author jgmat
 */

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>  {
    Optional<Funcionario> findByEmailAndSenha(String email, String password);

    
}
