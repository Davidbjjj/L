package curriculo.L.controller;

import curriculo.L.DTO.FormacaoDTO;
import curriculo.L.model.Formacao;
import curriculo.L.service.FormacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formacoes")
public class FormacaoController {

    @Autowired
    private FormacaoService formacaoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<FormacaoDTO> criar(@RequestBody FormacaoDTO dto) {
        Formacao formacao = modelMapper.map(dto, Formacao.class);
        Formacao salva = formacaoService.salvar(formacao);
        return ResponseEntity.ok(modelMapper.map(salva, FormacaoDTO.class));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<FormacaoDTO>> listarPorPessoa(@PathVariable Long pessoaId) {
        List<FormacaoDTO> lista = formacaoService.listarPorPessoa(pessoaId).stream()
                .map(f -> modelMapper.map(f, FormacaoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        formacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
