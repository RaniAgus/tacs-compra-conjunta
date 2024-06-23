package ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.model.TipoCosto;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.validation.EnumValue;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.validation.Range;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
@Range(min = "minPersonas", max = "maxPersonas", message = "La cantidad mínima de personas debe ser menor o igual a la cantidad máxima de personas")
public record CrearArticuloDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,

    @NotBlank(message = "La descripción no puede estar vacía")
    String descripcion,

    @NotBlank(message = "La imagen no puede estar vacía")
    @Pattern(regexp = "data:image/[^;]+;base64,(\\S+)", message = "La imagen no es válida")
    String imagen,

    @Nullable
    @Future(message = "La fecha límite debe ser futura")
    ZonedDateTime deadline,

    @NotNull(message = "La cantidad mínima de personas no puede estar vacía")
    @Positive(message = "La cantidad mínima de personas debe ser positiva")
    Integer minPersonas,

    @NotNull(message = "La cantidad máxima de personas no puede estar vacía")
    Integer maxPersonas,

    @NotNull(message = "El precio no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "El precio debe tener como máximo 2 decimales")
    BigDecimal precio,

    @NotEmpty(message = "El tipo de precio no puede estar vacío")
    @EnumValue(enumClass = TipoCosto.class, message = "El tipo de precio no es válido")
    String tipoPrecio
) {}
