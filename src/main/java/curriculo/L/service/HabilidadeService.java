package curriculo.L.service;

import curriculo.L.model.Habilidade;
import curriculo.L.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    public Habilidade salvar(Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    public List<Habilidade> listar() {
        return habilidadeRepository.findAll();
    }

    public Optional<Habilidade> buscarPorId(Long id) {
        return habilidadeRepository.findById(id);
    }

    public Habilidade atualizar(Long id, Habilidade habilidade) {
        if (habilidadeRepository.existsById(id)) {
            habilidade.setId(id);
            return habilidadeRepository.save(habilidade);
        }
        throw new RuntimeException("Habilidade não encontrada");
    }

    public void deletar(Long id) {
        if (habilidadeRepository.existsById(id)) {
            habilidadeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Habilidade não encontrada");
        }
    }
}
