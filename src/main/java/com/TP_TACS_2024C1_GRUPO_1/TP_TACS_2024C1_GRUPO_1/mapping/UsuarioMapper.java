package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.mapping;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.RegistrarseDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Rol;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.model.Usuario;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", uses = {PasswordEncoder.class})
public abstract class UsuarioMapper {
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "contrasenia", ignore = true)
    public abstract Usuario mapToUsuario(RegistrarseDTO registrarseDTO);

    @AfterMapping
    protected void afterMapToUsuario(@MappingTarget Usuario usuario, RegistrarseDTO registrarseDTO) {
        usuario.setContrasenia(passwordEncoder.encode(registrarseDTO.contrasenia()));
        usuario.setRoles(List.of(Rol.USUARIO));
    }
}
