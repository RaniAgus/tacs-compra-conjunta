package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.RegistrarseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "contrasenia", ignore = true)
    public abstract Usuario mapToUsuario(RegistrarseDTO registrarseDTO);

    public abstract UsuarioDTO mapToUsuarioDTO(Usuario usuario);

    @AfterMapping
    protected void afterMapToUsuario(@MappingTarget Usuario usuario, RegistrarseDTO registrarseDTO) {
        usuario.setContrasenia(passwordEncoder.encode(registrarseDTO.contrasenia()));
        usuario.setRoles(List.of(Rol.USUARIO));
    }
}
