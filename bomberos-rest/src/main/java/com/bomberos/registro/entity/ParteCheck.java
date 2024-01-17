package com.bomberos.registro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="parte_check")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParteCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long parteCheckId;

    @Column(name = "id_parte")
    private Long idParte;

    @Column(name = "id_registro_check")
    private Long idRegistroCheck;

    @Column(name = "is_ok")
    private boolean isOk;
}
