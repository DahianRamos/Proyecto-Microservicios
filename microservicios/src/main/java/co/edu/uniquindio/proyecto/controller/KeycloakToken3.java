package co.edu.uniquindio.proyecto.controller;


import co.edu.uniquindio.proyecto.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KeyloackToken", url = "http://localhost:9090/realms/tutorial-api/protocol/openid-connect/token")
public interface KeycloakToken3 {

        @PostMapping(consumes = "application/x-www-form-urlencoded")
        TokenDTO getToken(@RequestParam("grant_type") String grantType,
                          @RequestParam("client_id") String clientId,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password);
}
