package com.bomberos.registro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="parte")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long parteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_unidad")
    private Long idUnidad;

    @Column(name = "is_lateral")
    private boolean isLateral;
}
