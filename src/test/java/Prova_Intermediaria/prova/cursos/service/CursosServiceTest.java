package Prova_Intermediaria.prova.cursos.service;

import Prova_Intermediaria.prova.cursos.dto.CursosDTO;
import Prova_Intermediaria.prova.cursos.model.Cursos;
import Prova_Intermediaria.prova.cursos.repository.CursosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CursosServiceTest {

    @InjectMocks
    private CursosService service;

    @Mock
    private CursosRepository repository;

    @Test
    void test_salvarCurso() {
        CursosDTO dto = new CursosDTO();
        dto.setTitulo("Spring Boot");
        dto.setDescricao("Curso prático");
        dto.setCargaHoraria(30);
        dto.setInstrutor("Ana Souza");

        Cursos curso = new Cursos();
        curso.setId(1L);
        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setInstrutor(dto.getInstrutor());

        Mockito.when(repository.save(Mockito.any(Cursos.class))).thenReturn(curso);

        Cursos salvo = service.salvar(dto);

        assertNotNull(salvo);
        assertEquals("Spring Boot", salvo.getTitulo());
        assertEquals("Ana Souza", salvo.getInstrutor());
    }

    @Test
    void test_listarCursos() {
        Cursos curso = new Cursos();
        curso.setId(1L);
        curso.setTitulo("Java Básico");

        Mockito.when(repository.findAll()).thenReturn(List.of(curso));

        List<Cursos> cursos = service.listar();

        assertEquals(1, cursos.size());
        assertEquals("Java Básico", cursos.get(0).getTitulo());
    }

    @Test
    void test_deletarCurso() {
        Long id = 1L;

        service.deletar(id);

        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }
}
