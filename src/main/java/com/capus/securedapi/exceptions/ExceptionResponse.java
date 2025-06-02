package com.capus.securedapi.exceptions;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse {
    private Integer status;
    private String message;
}
