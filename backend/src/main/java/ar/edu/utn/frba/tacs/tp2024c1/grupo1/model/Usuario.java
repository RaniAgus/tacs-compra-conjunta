package ar.edu.utn.frba.tacs.tp2024c1.grupo1.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.beans.Transient;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Document("usuario")
public class Usuario implements UserDetails {
    @Id
    private String id;
    private String email;
    private String contrasenia;
    @Indexed(unique = true)
    private String nombreDeUsuario;
    @Setter(AccessLevel.NONE)
    private Set<Rol> roles = new HashSet<>();

    public void agregarRol(Rol rol) {
        roles.add(rol);
    }

    public Set<Rol> getRoles() {
        return Set.copyOf(roles);
    }

    @Transient
    public String getPassword() {
        return getContrasenia();
    }

    @Transient
    public String getUsername() {
        return getNombreDeUsuario();
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    public boolean isEnabled() {
        return true;
    }
}
