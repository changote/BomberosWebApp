package com.bomberos.registro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="registro")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long registroId;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "user")
    private String user;

    @Column(name = "id_unidad")
    private Long idUnidad;

}
