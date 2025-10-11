package com.adopcion.backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionApplicationRequest {
    private Long applicantId;
    private Long petId;
    private String message;
}
