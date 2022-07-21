package com.test.jwt.auth.controller;

import com.test.jwt.auth.dto.AdDTO;
import com.test.jwt.auth.dto.ResponseLoginDTO;
import com.test.jwt.auth.services.ConexionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;

@RestController
public class LoginController {

    @Autowired
    private ConexionAdService conexionAdService;

    @PostMapping(path = "/login")
    ResponseEntity<ResponseLoginDTO> login(@RequestBody AdDTO adDTO) throws ServerException {
        return new ResponseEntity<>(conexionAdService.conexionAd(adDTO), HttpStatus.OK);
    }


}
