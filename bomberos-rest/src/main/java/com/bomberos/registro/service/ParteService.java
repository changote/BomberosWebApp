package com.bomberos.registro.service;

import com.bomberos.registro.entity.Parte;
import com.bomberos.registro.repository.ParteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteService {
    @Autowired
    private ParteRepository parteRepository;

    public List<Parte> findAllByIdUnidad(Long idUnidad){
        return parteRepository.findAllByIdUnidad(idUnidad);
    }

    public void saveAll(List<Parte> parteList){
        parteRepository.saveAll(parteList);
    }

    public void deleteAll(List<Parte> parteList){
        parteRepository.deleteAll(parteList);
    }

}
