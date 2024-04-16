package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
    ADMIN,
    USUARIO,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
