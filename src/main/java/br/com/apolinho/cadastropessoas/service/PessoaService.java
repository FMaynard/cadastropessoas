package br.com.apolinho.cadastropessoas.service;

import br.com.apolinho.cadastropessoas.model.Pessoa;
import br.com.apolinho.cadastropessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pessoa> buscarPessoaPorId(UUID id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public Pessoa atualizarPessoa(UUID id, Pessoa novaPessoa) throws Exception {
        Optional<Pessoa> pessoaExistenteOpt = pessoaRepository.findById(id);
        if (pessoaExistenteOpt.isPresent()) {
            Pessoa pessoaExistente = pessoaExistenteOpt.get();
            pessoaExistente.setNome(novaPessoa.getNome());
            pessoaExistente.setDataNascimento(novaPessoa.getDataNascimento());
            pessoaExistente.setCidadeOrigem(novaPessoa.getCidadeOrigem());
            pessoaExistente.setTimeFutebol(novaPessoa.getTimeFutebol());
            return pessoaRepository.save(pessoaExistente);
        } else {
            throw new Exception("Pessoa não encontrada");
        }
    }

    @Transactional
    public void deletarPessoa(UUID id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pessoa não encontrada");
        }
    }
}

