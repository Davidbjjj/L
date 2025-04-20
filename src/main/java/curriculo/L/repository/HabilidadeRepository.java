package curriculo.L.repository;

import curriculo.L.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    List<Habilidade> findByPessoaId(Long pessoaId);
}

