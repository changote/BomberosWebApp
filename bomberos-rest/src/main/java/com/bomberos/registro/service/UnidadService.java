package com.bomberos.registro.service;

import com.bomberos.registro.dto.ParteDTO;
import com.bomberos.registro.dto.UnidadCompletaDTO;
import com.bomberos.registro.entity.Parte;
import com.bomberos.registro.entity.Unidad;
import com.bomberos.registro.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadService {

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private ParteService parteService;

    public List<Unidad> getUnidad(){
        return unidadRepository.findAll();
    }

    public Unidad findUnidadById(Long idUnidad){
        Optional<Unidad> possibleUnidad = unidadRepository.findById(idUnidad);
        if(possibleUnidad.isPresent())
            return possibleUnidad.get();
        throw new RuntimeException();
    }

    public UnidadCompletaDTO findUnidadCompletaById(Long idUnidad){
        UnidadCompletaDTO unidad = new UnidadCompletaDTO();
        Unidad unidadAux = findUnidadById(idUnidad);
        unidad.setIdUnidad(unidadAux.getUnidadId());
        unidad.setNombre(unidadAux.getNombre());
        unidad.setParteList(parteService.findAllByIdUnidad(idUnidad));
        return unidad;
    }
    @Transactional
    public Unidad nuevaUnidad(UnidadCompletaDTO request) {
        Unidad nuevaUnidad = new Unidad();
        nuevaUnidad.setNombre(request.getNombre());
        Unidad guardada = unidadRepository.save(nuevaUnidad);
        List<Parte> parteList = new ArrayList<>();
        for(ParteDTO parte : request.getParteListPost()){
            Parte parteAGuardar = new Parte();
            parteAGuardar.setIdUnidad(guardada.getUnidadId());
            parteAGuardar.setLateral(parte.isLateral());
            parteAGuardar.setNombre(parte.getNombre());
            parteList.add(parteAGuardar);
        }
        parteService.saveAll(parteList);
        return guardada;
    }
    @Transactional
    public List<Unidad> deleteUnidad(Long id){
        Optional<Unidad> unidadAborrar = unidadRepository.findById(id);
        if(unidadAborrar.isPresent()){
            Unidad unidad = unidadAborrar.get();
            unidadRepository.delete(unidad);
            parteService.deleteAll(parteService.findAllByIdUnidad(id));
        }
        return getUnidad();
    }
}
