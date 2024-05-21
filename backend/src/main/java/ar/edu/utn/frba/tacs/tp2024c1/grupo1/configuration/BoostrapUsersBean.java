package ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.EsUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BoostrapUsersBean {
    private final EsUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createInitialData() {
        return args -> {
            usuarioRepository.deleteAll();

            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setNombreDeUsuario("admin");
            usuarioAdmin.setEmail("admin@test.com");
            usuarioAdmin.setContrasenia(passwordEncoder.encode("contrasenia"));
            usuarioAdmin.agregarRol(Rol.ADMIN);

            usuarioRepository.save(usuarioAdmin);

            Usuario usuarioUser = new Usuario();
            usuarioUser.setNombreDeUsuario("user");
            usuarioUser.setEmail("usuario@test.com");
            usuarioUser.setContrasenia(passwordEncoder.encode("contrasenia"));
            usuarioUser.agregarRol(Rol.USUARIO);

            usuarioRepository.save(usuarioUser);
        };
    }
}
