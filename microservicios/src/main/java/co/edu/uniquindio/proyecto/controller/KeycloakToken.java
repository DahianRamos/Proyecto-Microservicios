package co.edu.uniquindio.proyecto.controller;


import co.edu.uniquindio.proyecto.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "KeycloakToken", url = "http://localhost:9090/realms/tutorial-api/protocol/openid-connect/token")
public interface KeycloakToken {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<TokenDTO> getToken(String parametros);


  }
