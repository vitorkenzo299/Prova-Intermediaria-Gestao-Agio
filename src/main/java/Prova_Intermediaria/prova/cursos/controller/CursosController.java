package Prova_Intermediaria.prova.cursos.controller;

import Prova_Intermediaria.prova.cursos.dto.CursosDTO;
import Prova_Intermediaria.prova.cursos.model.Cursos;
import Prova_Intermediaria.prova.cursos.service.CursosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    private final CursosService service;

    public CursosController(CursosService service) {
        this.service = service;
    }

    @PostMapping
    public Cursos cadastrar(@RequestBody CursosDTO dto) {
        return service.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping
    public List<Cursos> listar() {
        return service.listar();
    }
}
