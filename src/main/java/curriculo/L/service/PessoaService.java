package curriculo.L.service;

import curriculo.L.model.Pessoa;
import curriculo.L.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa atualizar(Long id, Pessoa novaPessoa) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(novaPessoa.getNome());
            pessoa.setEmail(novaPessoa.getEmail());
            pessoa.setTelefone(novaPessoa.getTelefone());
            pessoa.setResumo(novaPessoa.getResumo());
            return pessoaRepository.save(pessoa);
        }).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }
}
