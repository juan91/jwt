package com.test.jwt.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseLoginDTO {

    private boolean status;
    private String token;
}
