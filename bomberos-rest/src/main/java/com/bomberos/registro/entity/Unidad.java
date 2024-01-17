package com.bomberos.registro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="unidad")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long unidadId;

    @Column(name = "nombre")
    private String nombre;
}
