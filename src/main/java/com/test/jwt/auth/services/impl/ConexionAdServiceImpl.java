package com.test.jwt.auth.services.impl;

import com.test.jwt.auth.dto.AdDTO;
import com.test.jwt.auth.dto.ResponseLoginDTO;
import com.test.jwt.auth.services.ConexionAdService;
import com.test.jwt.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.ServerException;
import java.util.UUID;

@Service
@Slf4j
public class ConexionAdServiceImpl implements ConexionAdService {
    //private DirectoryActiveRepository directoryActiveRepository;

//    public ConexionAdServiceImpl(DirectoryActiveRepository directoryActiveRepository){
//        this.directoryActiveRepository= directoryActiveRepository;
//    }

    @Autowired
    private Util util;

    @Override
    public ResponseLoginDTO conexionAd(AdDTO adDTO) throws ServerException {
        //simulamos respuesta de directoryActiveRepository como exitosa
        //AdDTO response = directoryActiveRepository.conexionAd(adDTO);
        AdDTO response =  AdDTO.builder()
                .Password("12345")
                .Usuario("pepe")
                .viip("192.168.0.1")
                .vinavegador("chrome")
                .voemail("epep@gmail.com")
                .voerror("")
                .voidconexion("123456")
                .vonombrecompleto("pepe pepe")
                .build();
        String token = null;
        if(response.voerror != null && response.getVoidconexion() != null) {
            try {
                String uuid = UUID.randomUUID().toString();
                //token caduca cada 20minutos
                 token = util.generarJwtToken(uuid, 1200000L, response.getVoemail(), response.getUsuario());
            } catch (Exception e) {
                log.error(e.toString());
                throw new ServerException("Error generando token");
            }
        } else {
            return ResponseLoginDTO.builder()
                    .status(false)
                    .token("Usuario no valido")
                    .build();
        }
        return ResponseLoginDTO.builder()
                .status(true)
                .token(token)
                .build();

    }

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";



    @Override
    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}
