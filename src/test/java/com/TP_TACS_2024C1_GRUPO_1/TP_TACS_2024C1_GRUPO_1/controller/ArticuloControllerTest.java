package com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.ArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.dto.CrearArticuloDTO;
import com.TP_TACS_2024C1_GRUPO_1.TP_TACS_2024C1_GRUPO_1.service.ArticuloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ArticuloControllerTest {
    @MockBean
    private ArticuloService articuloService;

    @Autowired
    private MockMvc mockMvc;

    @Test
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
                    "maxPersonas": 1
                }
                """;

        when(articuloService.crearArticulo(any(CrearArticuloDTO.class))).thenReturn(ArticuloDTO.builder().build());

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content)).andExpect(status().isOk());
    }

    @Test
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

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.global[0]").value("La cantidad mínima de personas debe ser menor o igual a la cantidad máxima de personas"));
    }

    @Test
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

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.imagen[0]").value("La imagen no es válida"));
    }

    @Test
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

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.precio[0]").value("El precio debe tener como máximo 2 decimales"));
    }

    @Test
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

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.tipoPrecio[0]").value("El tipo de precio no es válido"));
    }

    @Test
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

        this.mockMvc.perform(post("/articulos").contentType("application/json").content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.fields.nombre[0]").value("El nombre no puede estar vacío"));
    }
}
