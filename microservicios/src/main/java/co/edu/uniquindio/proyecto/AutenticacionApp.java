package co.edu.uniquindio.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutenticacionApp {

    public static void main(String[] args) {SpringApplication.run(AutenticacionApp.class, args);}
}
