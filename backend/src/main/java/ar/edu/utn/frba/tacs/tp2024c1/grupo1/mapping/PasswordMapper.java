package ar.edu.utn.frba.tacs.tp2024c1.grupo1.mapping;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordMapper {
    private final PasswordEncoder passwordEncoder;

    @Named("password")
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
