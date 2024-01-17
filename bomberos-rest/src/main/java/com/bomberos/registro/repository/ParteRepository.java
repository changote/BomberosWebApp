package com.bomberos.registro.repository;

import com.bomberos.registro.entity.Parte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParteRepository extends JpaRepository<Parte, Long> {

    List<Parte> findAllByIdUnidad(Long idUnidad);
}
