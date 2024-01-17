package com.bomberos.registro.service;

import com.bomberos.registro.entity.RegistroCheck;
import com.bomberos.registro.repository.RegistroCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroCheckService {
    @Autowired
    private RegistroCheckRepository registroCheckRepository;

    public RegistroCheck save(RegistroCheck registro){
        return registroCheckRepository.save(registro);
    }

    public List<RegistroCheck> saveAll(List<RegistroCheck> checkList) {
        return registroCheckRepository.saveAll(checkList);
    }

    public RegistroCheck findByLateralIsAndIdRegistro(boolean isLateral, Long idRegistro){
        return registroCheckRepository.findByIdRegistroAndIsLateral(idRegistro, isLateral);
    }
}
