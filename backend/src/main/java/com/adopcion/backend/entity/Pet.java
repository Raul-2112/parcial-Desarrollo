package com.adopcion.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
@Data

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 50)
    private String species; // Perro, Gato, etc.

    @Column(length = 100)
    private String breed;

    @Column
    private Integer age; // en meses o años (documentar convención)

    @Column(length = 10)
    private String gender; // Macho / Hembra

    @Column(length = 500)
    private String description;

    @Column(name = "image_url", length = 500)
    private String imageUrl; // url o ruta local de la foto principal

    @Column(name = "available", nullable = false)
    private Boolean available = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "refuge_id")
    private Refuge refuge;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdoptionApplication> applications;
}
