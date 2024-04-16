package ar.edu.utn.frba.tacs.tp2024c1.grupo1.configuration;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BoostrapUsersBean {
    private final UsuarioRepository usuarioRepository;

    @Bean
    public CommandLineRunner createInitialData() {
        return args -> {
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setNombreDeUsuario("admin");
            usuarioAdmin.setEmail("admin@test.com");
            usuarioAdmin.setContrasenia("$2a$10$SqLBWQ2PmuNucbm4TmrBM.n3yTUBfQxBu4wm6HKIjoaPkFtZw3/ZC"); // la palabra "contrasenia" encodeada
            usuarioAdmin.setRoles(List.of(Rol.ADMIN));

            usuarioRepository.save(usuarioAdmin);

            Usuario usuarioUser = new Usuario();
            usuarioUser.setNombreDeUsuario("user");
            usuarioUser.setEmail("usuario@test.com");
            usuarioUser.setContrasenia("$2a$10$SqLBWQ2PmuNucbm4TmrBM.n3yTUBfQxBu4wm6HKIjoaPkFtZw3/ZC"); // la palabra "contrasenia" encodeada
            usuarioUser.setRoles(List.of(Rol.USUARIO));

            usuarioRepository.save(usuarioUser);
        };
    }
}
