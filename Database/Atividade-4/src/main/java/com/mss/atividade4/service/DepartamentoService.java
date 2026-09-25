package com.mss.atividade4.service;

import com.mss.atividade4.entity.Departamento;
import com.mss.atividade4.entity.Funcionario;
import com.mss.atividade4.repository.DepartamentoRepository;
import com.mss.atividade4.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository  repository;

    // Atividade Transação - Questão 5.
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Departamento> listarTodosDepartamentos() {
        return repository.findAll();
    }

    public Departamento listarPrimeiroDepartamento() {
        return repository.findTopByOrderByIdAsc();
    }

    // Atividade Transação - Questão 5
    @Transactional
    public Funcionario salvarDepartamentoComFuncionario(Departamento departamento, Funcionario funcionario) {
        Departamento departamentoSalvo = repository.save(departamento);

        funcionario.setDepartamento(departamentoSalvo);

        return funcionarioRepository.save(funcionario);
    }
}
