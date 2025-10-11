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
@Table(name = "refuges")
@Data

public class Refuge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 300)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 200)
    private String email;

    @Column(length = 500)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Opcional: si quieres enlazar propietario (User) descomenta estas líneas y ajusta
    // @ManyToOne
    // @JoinColumn(name = "owner_id")
    // private User owner;

    @OneToMany(mappedBy = "refuge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
}
