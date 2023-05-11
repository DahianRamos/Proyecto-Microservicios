
package service;


import controller.AutenticacionController;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repo.UserRepo;

@Service
@AllArgsConstructor
public class LoginServicio {

    private  AutenticacionController autenticacionController;

    private  UserRepo userRepo;
    private  PasswordEncoder passwordEncoder;

     /**
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword()));
            User user = userRepository.findByUsername(loginDTO.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: "
                            + loginDTO.getUsername()));
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            return new TokenDTO(token);
        } catch (AuthenticationException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }
    }

    public TokenDTO refresh(TokenDTO tokenDTO) {
        String token = tokenDTO.getToken();
        String username = jwtTokenProvider.getUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username));
        if (jwtTokenProvider.validateToken(token) && jwtTokenProvider.getUsername(token).equals(username)) {
            return new TokenDTO(jwtTokenProvider.createToken(username, user.getRoles()));
        } else {
            throw new InvalidJwtAuthenticationException("Token inválido");
        }
    }

    public boolean createUser(NewUserDTO newUserDTO) {
        if (userRepository.existsByUsername(newUserDTO.getUsername())) {
            return false;
        }
        User user = new User();
        user.setUsername(newUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setRoles(Collections.singletonList(Role.USER));
        userRepository.save(user);
        return true;
    }
      */
}
