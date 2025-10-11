package com.adopcion.backend.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RefugeRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
}