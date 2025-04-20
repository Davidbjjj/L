package curriculo.L.service;

import curriculo.L.model.Experiencia;
import curriculo.L.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaService {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    public Experiencia salvar(Experiencia experiencia) {
        return experienciaRepository.save(experiencia);
    }

    public List<Experiencia> listar() {
        return experienciaRepository.findAll();
    }

    public Optional<Experiencia> buscarPorId(Long id) {
        return experienciaRepository.findById(id);
    }

    public Experiencia atualizar(Long id, Experiencia experiencia) {
        if (experienciaRepository.existsById(id)) {
            experiencia.setId(id);
            return experienciaRepository.save(experiencia);
        }
        throw new RuntimeException("Experiência não encontrada");
    }

    public void deletar(Long id) {
        if (experienciaRepository.existsById(id)) {
            experienciaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Experiência não encontrada");
        }
    }
}
