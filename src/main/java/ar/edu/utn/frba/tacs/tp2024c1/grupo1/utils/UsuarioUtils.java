package ar.edu.utn.frba.tacs.tp2024c1.grupo1.utils;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class UsuarioUtils {
    public static Usuario obtenerUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }
}
