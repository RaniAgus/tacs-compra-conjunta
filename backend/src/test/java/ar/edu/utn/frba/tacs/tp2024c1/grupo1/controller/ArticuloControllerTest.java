package ar.edu.utn.frba.tacs.tp2024c1.grupo1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ar.edu.utn.frba.tacs.tp2024c1.grupo1.component.JwtFilter;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.ArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.dto.CrearArticuloDTO;
import ar.edu.utn.frba.tacs.tp2024c1.grupo1.service.ArticuloService;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
        controllers = ArticuloController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JwtFilter.class})
)
class ArticuloControllerTest {
    @MockBean
    private ArticuloService articuloService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloValido() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 1,
                    "maxPersonas": 1,
                    "deadline": "%s"
                }
                """.formatted(ZonedDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        given(articuloService.crearArticulo(any(CrearArticuloDTO.class))).willReturn(ArticuloDTO.builder().build());

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConMinPersonasMayorQueMaxPersonas() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 2,
                    "maxPersonas": 1
                }
                """;

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.global[0]").value("La cantidad mínima de personas debe ser menor o igual a la cantidad máxima de personas"));
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConImagenNoValida() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 1,
                    "maxPersonas": 1
                }
                """;

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.imagen[0]").value("La imagen no es válida"));
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConPrecioNoValido() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.987",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 1,
                    "maxPersonas": 1
                }
                """;

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.precio[0]").value("El precio debe tener como máximo 2 decimales"));
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConTipoPrecioInvalido() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_GRILLO",
                    "minPersonas": 1,
                    "maxPersonas": 1
                }
                """;

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.tipoPrecio[0]").value("El tipo de precio no es válido"));
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConNombreVacio() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 1,
                    "maxPersonas": 1
                }
                """;

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.nombre[0]").value("El nombre no puede estar vacío"));
    }

    @Test
    @WithMockUser(authorities = "USUARIO")
    void crearArticuloConFechaPasada() throws Exception {
        // language=json
        var content = """
                {
                    "nombre": "pepito",
                    "descripcion": "grillo",
                    "precio": "19.98",
                    "imagen": "data:image/gif;base64,R0lGODlhAQABAAAAACw=",
                    "tipoPrecio": "POR_PERSONA",
                    "minPersonas": 1,
                    "maxPersonas": 1,
                    "deadline": "%s"
                }
                """.formatted(ZonedDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        this.mockMvc.perform(post("/articulos").contentType(MediaType.APPLICATION_JSON).content(content).with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.deadline[0]").value("La fecha límite debe ser futura"));
    }
}
