package com.adopcion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adoption_applications")
@Data

public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quien solicita
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User applicant;

    // Mascota objetivo
    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(length = 20)
    private String status = "PENDIENTE"; // PENDIENTE, APROBADA, RECHAZADA

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

