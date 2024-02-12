package com.gamg.contaclist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ContactDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String numberPhone;
    @NotBlank
    @Email
    private String email;
    private Date dateRegister;
}
