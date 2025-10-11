package com.adopcion.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequest {
    private String name;
    private String species;
    private String breed;
    private Integer age;
    private String gender;
    private String imageUrl;
    private String description;
    private Boolean available;
    private Long refugeId; // FK reference
}