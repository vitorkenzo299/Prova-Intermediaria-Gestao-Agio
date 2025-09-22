package Prova_Intermediaria.prova.cursos.controller;

import Prova_Intermediaria.prova.cursos.dto.CursosDTO;
import Prova_Intermediaria.prova.cursos.model.Cursos;
import Prova_Intermediaria.prova.cursos.service.CursosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CursosControllerTest {

    @InjectMocks
    private CursosController controller;

    @Mock
    private CursosService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void test_listarCursos() throws Exception {
        Cursos curso = new Cursos();
        curso.setId(1L);
        curso.setTitulo("Java Básico");
        curso.setDescricao("Introdução ao Java");
        curso.setCargaHoraria(40);
        curso.setInstrutor("Maria Silva");
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(service.listar()).thenReturn(List.of(curso));

        mockMvc.perform(MockMvcRequestBuilders.get("/cursos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Java Básico"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].instrutor").value("Maria Silva"));
    }

    @Test
    void test_cadastrarCurso() throws Exception {
        CursosDTO dto = new CursosDTO();
        dto.setTitulo("Spring Boot");
        dto.setDescricao("Curso prático de API");
        dto.setCargaHoraria(20);
        dto.setInstrutor("João Souza");

        Cursos curso = new Cursos();
        curso.setId(1L);
        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setInstrutor(dto.getInstrutor());
        curso.setDataCadastro(LocalDate.now());

        Mockito.when(service.salvar(Mockito.any(CursosDTO.class))).thenReturn(curso);

        mockMvc.perform(MockMvcRequestBuilders.post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Spring Boot\",\"descricao\":\"Curso prático de API\",\"cargaHoraria\":20,\"instrutor\":\"João Souza\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Spring Boot"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.instrutor").value("João Souza"));
    }
}
