package com.mss.atividade4.repository;

import com.mss.atividade4.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Departamento findTopByOrderByIdAsc();

}
