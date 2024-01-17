package com.bomberos.registro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="registro_check")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long registroCheckId;

    @Column(name = "id_registro")
    private Long idRegistro;

    @Column(name = "is_lateral")
    private boolean isLateral;

    @Column(name = "observacion")
    private String observacion;
}
