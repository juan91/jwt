package com.test.jwt.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdDTO {

    public String Usuario;
    public String Password;
    public String vinavegador;
    public String viip;
    public String voidconexion;
    public String vonombrecompleto;
    public String voemail;
    public String voerror;

}
