package com.shooza.demo.configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class IpLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String ip = request.getRemoteAddr(); // IP directe
        String forwarded = httpRequest.getHeader("X-Forwarded-For"); // si derrière un proxy

        if (forwarded != null) {
            System.out.println("Requête reçue de IP (proxy): " + forwarded);
        } else {
            System.out.println("Requête reçue de IP: " + ip);
        }
        if (Objects.equals(ip, "192.168.0.150")) {
            throw new RuntimeException("Said");
        }

        chain.doFilter(request, response);
    }
}
