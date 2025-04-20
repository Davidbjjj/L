package curriculo.L.controller;

import curriculo.L.DTO.PessoaDTO;
import curriculo.L.model.Pessoa;
import curriculo.L.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoas", produces = "application/json")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);
        Pessoa salva = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(modelMapper.map(salva, PessoaDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        List<PessoaDTO> lista = pessoaService.listar().stream()
                .map(p -> modelMapper.map(p, PessoaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(p -> ResponseEntity.ok(modelMapper.map(p, PessoaDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        Pessoa atualizada = pessoaService.atualizar(id, modelMapper.map(dto, Pessoa.class));
        return ResponseEntity.ok(modelMapper.map(atualizada, PessoaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

