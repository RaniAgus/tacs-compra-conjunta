package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.RegistrarseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.mapstruct.*;

@Mapper(uses = PasswordMapper.class)
public interface UsuarioMapper {
    @Mapping(target = "contrasenia", qualifiedByName = "password")
    Usuario mapToUsuario(RegistrarseDTO registrarseDTO);

    UsuarioDTO mapToUsuarioDTO(Usuario usuario);

    @AfterMapping
    default void afterMapRegistrarseDTOToUsuario(@MappingTarget Usuario usuario, RegistrarseDTO registrarseDTO) {
        usuario.agregarRol(Rol.USUARIO);
    }
}
