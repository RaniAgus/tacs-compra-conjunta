package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.configuration;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Rol;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.repository.UsuarioRepository;
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
