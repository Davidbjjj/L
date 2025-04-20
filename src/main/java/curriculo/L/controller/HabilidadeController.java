package curriculo.L.controller;

import curriculo.L.DTO.HabilidadeDTO;
import curriculo.L.model.Habilidade;
import curriculo.L.service.HabilidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/habilidades", produces = "application/json")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<HabilidadeDTO> criar(@RequestBody HabilidadeDTO dto) {
        Habilidade habilidade = modelMapper.map(dto, Habilidade.class);
        Habilidade salva = habilidadeService.salvar(habilidade);
        return ResponseEntity.ok(modelMapper.map(salva, HabilidadeDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<HabilidadeDTO>> listar() {
        List<HabilidadeDTO> lista = habilidadeService.listar().stream()
                .map(h -> modelMapper.map(h, HabilidadeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabilidadeDTO> buscar(@PathVariable Long id) {
        return habilidadeService.buscarPorId(id)
                .map(h -> ResponseEntity.ok(modelMapper.map(h, HabilidadeDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabilidadeDTO> atualizar(@PathVariable Long id, @RequestBody HabilidadeDTO dto) {
        Habilidade atualizada = habilidadeService.atualizar(id, modelMapper.map(dto, Habilidade.class));
        return ResponseEntity.ok(modelMapper.map(atualizada, HabilidadeDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        habilidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
