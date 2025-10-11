package com.adopcion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adoptions")
@Data
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // La solicitud que originó la adopción (opcional duplicado de datos)
    @OneToOne
    @JoinColumn(name = "application_id", nullable = false)
    private AdoptionApplication application;

    // Usuario que adopta (redundante con application.adopter pero útil en reporte)
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User adopter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "adopted_at", nullable = false)
    private LocalDateTime adoptedAt = LocalDateTime.now();

    @Column(length = 1000)
    private String notes;
}
