package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.RegistrarseDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.UsuarioDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Rol;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.Usuario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = PasswordMapper.class)
public interface UsuarioMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrasenia", qualifiedByName = "password")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Usuario mapToUsuario(RegistrarseDTO registrarseDTO);

    UsuarioDTO mapToUsuarioDTO(Usuario usuario);

    @AfterMapping
    default void afterMapRegistrarseDTOToUsuario(@MappingTarget Usuario usuario, RegistrarseDTO registrarseDTO) {
        usuario.agregarRol(Rol.USUARIO);
    }
}
