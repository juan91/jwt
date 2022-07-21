package com.test.jwt.auth.services;

import com.test.jwt.auth.dto.AdDTO;
import com.test.jwt.auth.dto.ResponseLoginDTO;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;

public interface ConexionAdService {
    ResponseLoginDTO conexionAd(AdDTO adDTO) throws ServerException;

    String getClientIp(HttpServletRequest request);
}
