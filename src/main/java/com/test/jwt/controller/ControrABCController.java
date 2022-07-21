package com.test.jwt.controller;

import com.test.jwt.utils.Util;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/control")
public class ControrABCController {

    @Autowired
    Util util;

    // controlador de prueba, si no se asigna token por header desde el cliente, generera error 401
    @GetMapping()
    String obtenerControll(){
            return "test jwt existoso :)";
    }

}
