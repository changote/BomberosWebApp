package com.bomberos.registro.repository;

import com.bomberos.registro.entity.RegistroCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroCheckRepository extends JpaRepository<RegistroCheck, Long> {

    RegistroCheck findByIdRegistroAndIsLateral(Long idRegistro, boolean isLateral);
}
