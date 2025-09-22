package Prova_Intermediaria.prova.cursos.service;

import Prova_Intermediaria.prova.cursos.dto.CursosDTO;
import Prova_Intermediaria.prova.cursos.model.Cursos;
import Prova_Intermediaria.prova.cursos.repository.CursosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosService {

    private final CursosRepository repository;

    public CursosService(CursosRepository repository) {
        this.repository = repository;
    }

    public Cursos salvar(CursosDTO dto) {
        Cursos curso = new Cursos();
        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setInstrutor(dto.getInstrutor());
        return repository.save(curso);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public List<Cursos> listar() {
        return repository.findAll();
    }
}
