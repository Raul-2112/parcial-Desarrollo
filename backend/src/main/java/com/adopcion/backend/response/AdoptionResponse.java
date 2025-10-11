package com.adopcion.backend.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdoptionResponse {
    private Long id;
    private Long applicationId;
    private Long adopterId;
    private Long petId;
    private LocalDateTime adoptedAt;
    private String notes;
}
