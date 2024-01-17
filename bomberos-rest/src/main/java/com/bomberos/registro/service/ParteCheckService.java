package com.bomberos.registro.service;

import com.bomberos.registro.entity.ParteCheck;
import com.bomberos.registro.repository.ParteCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteCheckService {
    @Autowired
    private ParteCheckRepository parteCheckRepository;

    public void saveAll(List<ParteCheck> parteCheckList){
        parteCheckRepository.saveAll(parteCheckList);
    }


}
