package curriculo.L.controller;

import curriculo.L.DTO.ExperienciaDTO;
import curriculo.L.model.Experiencia;
import curriculo.L.service.ExperienciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/experiencias", produces = "application/json")
public class ExperienciaController {

    @Autowired
    private ExperienciaService experienciaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ExperienciaDTO> criar(@RequestBody ExperienciaDTO dto) {
        Experiencia experiencia = modelMapper.map(dto, Experiencia.class);
        Experiencia salva = experienciaService.salvar(experiencia);
        return ResponseEntity.ok(modelMapper.map(salva, ExperienciaDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaDTO>> listar() {
        List<ExperienciaDTO> lista = experienciaService.listar().stream()
                .map(e -> modelMapper.map(e, ExperienciaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaDTO> buscar(@PathVariable Long id) {
        return experienciaService.buscarPorId(id)
                .map(e -> ResponseEntity.ok(modelMapper.map(e, ExperienciaDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaDTO> atualizar(@PathVariable Long id, @RequestBody ExperienciaDTO dto) {
        Experiencia atualizada = experienciaService.atualizar(id, modelMapper.map(dto, Experiencia.class));
        return ResponseEntity.ok(modelMapper.map(atualizada, ExperienciaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        experienciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
