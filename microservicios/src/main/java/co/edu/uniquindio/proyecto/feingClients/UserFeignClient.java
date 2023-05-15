package co.edu.uniquindio.proyecto.feingClients;

import co.edu.uniquindio.proyecto.dto.ResponseGetUserDTO;
import co.edu.uniquindio.proyecto.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "UserFleignClient", url = "${keycloack.admin-url}")
public interface UserFeignClient {

    @PostMapping("/users")
    ResponseEntity<String> create(@RequestBody UserDTO userDTO, @RequestHeader(value = "Authorization") String bearerToken);

    @GetMapping("/users/")
    ResponseEntity<List<ResponseGetUserDTO>> getUserId(@RequestParam String username, @RequestHeader(value = "Authorization") String bearerToken);

    @PostMapping("/users/{userId}/role-mappings/realm")
    ResponseEntity<Void> setRole(@PathVariable String userId, @RequestBody List<Map<String, String>> role, @RequestHeader(value = "Authorization") String bearerToken);
}

