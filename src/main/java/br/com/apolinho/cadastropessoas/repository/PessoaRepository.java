package br.com.apolinho.cadastropessoas.repository;

import br.com.apolinho.cadastropessoas.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
