package curriculo.L.controller;

import curriculo.L.DTO.ProjetoDTO;
import curriculo.L.model.Projeto;
import curriculo.L.service.ProjetoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/projetos", produces = "application/json")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProjetoDTO> criar(@RequestBody ProjetoDTO dto) {
        Projeto projeto = modelMapper.map(dto, Projeto.class);
        Projeto salvo = projetoService.salvar(projeto);
        return ResponseEntity.ok(modelMapper.map(salvo, ProjetoDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> listar() {
        List<ProjetoDTO> lista = projetoService.listar().stream()
                .map(p -> modelMapper.map(p, ProjetoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> buscar(@PathVariable Long id) {
        return projetoService.buscarPorId(id)
                .map(p -> ResponseEntity.ok(modelMapper.map(p, ProjetoDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualizar(@PathVariable Long id, @RequestBody ProjetoDTO dto) {
        Projeto atualizado = projetoService.atualizar(id, modelMapper.map(dto, Projeto.class));
        return ResponseEntity.ok(modelMapper.map(atualizado, ProjetoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
