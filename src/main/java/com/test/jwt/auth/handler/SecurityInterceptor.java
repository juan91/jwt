package com.test.jwt.auth.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final String AUTH_HEADER_PARAMETER_AUTHERIZATION = "authorization";

    private static final String AUTH_HEADER_PARAMETER_BEARER = "Bearer ";

    private static final String SECRET_KEY = "ejkdrtb3478545io6gh89efhn3o4vnmceop;run349832";

    // antes de cualquier petición a los controladores siempre entrará por este método
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(request.getRequestURI().equals("/login")) {
            return true;
        }
        String jwtAuthToken = null;

        System.out.println("[Inside PRE Handle interceptor][" + request + "]" + "[" + request.getMethod() + "]"
                + request.getRequestURI());

        try {
            // Get JWT token from header value
            jwtAuthToken = request.getHeader(AUTH_HEADER_PARAMETER_AUTHERIZATION).replace(AUTH_HEADER_PARAMETER_BEARER,"");
            // Validate JWT token using public key
            decodificarJwt(jwtAuthToken);
            return true;
        } catch (Exception e) {
            System.out.println("Error occured while authenticating request : " + e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("[Inside POST Handle Interceptor]" + request.getRequestURI());
    }


public Claims decodificarJwt(String jwt) {
    return Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .build()
            .parseClaimsJws(jwt).getBody();
}

}