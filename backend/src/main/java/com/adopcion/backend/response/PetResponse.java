package com.adopcion.backend.response;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PetResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private String gender;
    private String description;
    private String imageUrl;
    private Boolean available;
    private LocalDateTime createdAt;
    private Long refugeId;
}
