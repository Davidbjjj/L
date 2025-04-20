package curriculo.L.service;

import curriculo.L.model.Formacao;
import curriculo.L.repository.FormacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormacaoService {

    @Autowired
    private FormacaoRepository formacaoRepository;

    public Formacao salvar(Formacao formacao) {
        return formacaoRepository.save(formacao);
    }

    public List<Formacao> listarPorPessoa(Long pessoaId) {
        return formacaoRepository.findByPessoaId(pessoaId);
    }

    public void deletar(Long id) {
        formacaoRepository.deleteById(id);
    }
}
