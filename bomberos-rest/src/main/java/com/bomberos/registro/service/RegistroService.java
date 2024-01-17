package com.bomberos.registro.service;

import com.bomberos.registro.dto.RegistroCompletoDTO;
import com.bomberos.registro.dto.RegistroReporteDTO;
import com.bomberos.registro.entity.ParteCheck;
import com.bomberos.registro.entity.Registro;
import com.bomberos.registro.entity.RegistroCheck;
import com.bomberos.registro.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private RegistroCheckService registroCheckService;
    @Autowired
    private ParteCheckService parteCheckService;

    private final EntityManager entityManager;

    public RegistroService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public String nuevoRegistro(RegistroCompletoDTO request) {
        Registro nuevoRegistro = new Registro();
        nuevoRegistro.setUser(request.getUser());
        nuevoRegistro.setFecha(LocalDateTime.now());
        nuevoRegistro.setIdUnidad(request.getIdUnidad());
        Registro guardado = registroRepository.save(nuevoRegistro);
        RegistroCheck lateralGuardado = registroCheckService.save(requestToRegistroCheck(request.getLateral(),guardado.getRegistroId()));
        RegistroCheck motorGuardado = new RegistroCheck();
        if(request.getMotor() != null)
            motorGuardado = registroCheckService.save(requestToRegistroCheck(request.getMotor(),guardado.getRegistroId()));
        parteCheckService.saveAll(requestToParteCheck(request.getLateral().getParteList(),lateralGuardado.getRegistroCheckId()));
        if(request.getMotor() != null)
            parteCheckService.saveAll(requestToParteCheck(request.getMotor().getParteList(),motorGuardado.getRegistroCheckId()));
        return "OK";
    }

    private RegistroCheck requestToRegistroCheck(RegistroCompletoDTO.RegistroCheckDTO registroCheck, Long registroId) {
        RegistroCheck nuevoRegistroCheck = new RegistroCheck();
        nuevoRegistroCheck.setIdRegistro(registroId);
        nuevoRegistroCheck.setLateral(registroCheck.isLateral());
        nuevoRegistroCheck.setObservacion(registroCheck.getObservacion());
        return nuevoRegistroCheck;
    }

    private List<ParteCheck> requestToParteCheck(List<RegistroCompletoDTO.RegistroCheckDTO.ParteCheckDTO>requestList, Long registroCheckId){
        List<ParteCheck> parteCheckList = new ArrayList<>();
        for(RegistroCompletoDTO.RegistroCheckDTO.ParteCheckDTO registroCheck : requestList){
            ParteCheck nuevoParteCheck = new ParteCheck();
            nuevoParteCheck.setIdRegistroCheck(registroCheckId);
            nuevoParteCheck.setIdParte(registroCheck.getIdParte());
            nuevoParteCheck.setOk(registroCheck.isOk());
            parteCheckList.add(nuevoParteCheck);
        }
        return parteCheckList;
    }

    public List<RegistroReporteDTO> getRegistrosByUnidadId(Long id) {
        List<RegistroReporteDTO> listaRegistro = new ArrayList<>();
        String nativeQuery = "SELECT " +
                "  MIN(CASE WHEN p1.is_lateral = true THEN pc.is_ok ELSE NULL END) AS laterales, " +
                "  MIN(CASE WHEN p2.is_lateral = false THEN pc.is_ok ELSE NULL END) AS motores, " +
                "  r.fecha, r.user " +
                "FROM parte_check pc " +
                "JOIN parte p1 ON p1.id = pc.id_parte " +
                "JOIN parte p2 ON p2.id = pc.id_parte " +
                "JOIN registro r ON r.id_unidad = p1.id_unidad " +
                "JOIN registro_check rc ON rc.id_registro = r.id " +
                "WHERE p1.id_unidad = :id AND p2.id_unidad = :id AND rc.id = pc.id_registro_check " +
                "GROUP BY r.id " +
                "ORDER BY r.fecha DESC;";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter("id", id);

        @SuppressWarnings("unchecked")
        List<Object[]> auxObject = query.getResultList();

        for (Object[] result : auxObject) {
            RegistroReporteDTO registro = new RegistroReporteDTO();
            registro.setLateralOk(formatearBoolean(result[0].toString()));
            registro.setMotorOk((result[1] != null) ? formatearBoolean(result[1].toString()) : true);
            registro.setFecha(formatearFecha(result[2].toString()));
            registro.setUser(result[3].toString());
            listaRegistro.add(registro);
        }
        return listaRegistro;
    }
    public static Boolean formatearBoolean(String request){
        return (!request.equals("0"));
    }
    public static String formatearFecha(String fechaOriginal) {
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Date fecha = formatoOriginal.parse(fechaOriginal);
            return formatoNuevo.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace(); // Manejo de errores al parsear la fecha
            return null;
        }
    }
}
