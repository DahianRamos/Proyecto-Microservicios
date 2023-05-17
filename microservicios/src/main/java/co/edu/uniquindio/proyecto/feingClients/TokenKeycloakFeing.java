package co.edu.uniquindio.proyecto.feingClients;


import co.edu.uniquindio.proyecto.dto.TokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "TokenKeycloakFeing", url = "${URL_aut}")
public interface TokenKeycloakFeing {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<TokenResponseDTO> sendRequest(String parametros);


  }
