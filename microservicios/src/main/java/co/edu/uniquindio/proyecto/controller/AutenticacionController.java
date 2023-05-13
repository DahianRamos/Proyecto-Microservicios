package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.NewUserDTO;
import co.edu.uniquindio.proyecto.dto.Respuesta;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.service.LoginServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AutenticacionController {



    private final LoginServicio loginServicio;

    private final KeycloakToken3 keycloakToken;
  //Realizar metodo que recibe el token de keyloack usando feing
    // averiguar sobre el requestbody del token que llegara por el servidor de keyloack



    @PostMapping("/signin")
    public ResponseEntity<Respuesta<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Login correcto", loginServicio.login(loginDTO)) );
    }

    @PostMapping("/refresh")
    public ResponseEntity<Respuesta<TokenDTO>> refresh(HttpServletRequest request) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", loginServicio.refresh(new TokenDTO(token))) );
    }

    @PostMapping("/signup")
    public ResponseEntity<Respuesta<String>> createUser(@RequestBody NewUserDTO newUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO) ? "Creado correctamente": "Error", "") );
    }



}
