package br.com.apolinho.cadastropessoas;

import br.com.apolinho.cadastropessoas.model.Pessoa;
import br.com.apolinho.cadastropessoas.repository.PessoaRepository;
import br.com.apolinho.cadastropessoas.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private PessoaService pessoaService;

	@Test
	public void testSalvarPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

		Pessoa resultado = pessoaService.salvarPessoa(pessoa);
		assertNotNull(resultado);
		assertEquals("João", resultado.getNome());
	}

	@Test
	public void testListarPessoas() {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("João");
		pessoa1.setDataNascimento(LocalDate.of(1990, 1, 1));

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Maria");
		pessoa2.setDataNascimento(LocalDate.of(1985, 5, 5));

		when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));

		List<Pessoa> pessoas = pessoaService.listarPessoas();
		assertEquals(2, pessoas.size());
		assertEquals("João", pessoas.get(0).getNome());
		assertEquals("Maria", pessoas.get(1).getNome());
	}

	@Test
	public void testBuscarPessoaPorId() {
		UUID id = UUID.randomUUID();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

		when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

		Optional<Pessoa> resultado = pessoaService.buscarPessoaPorId(id);
		assertTrue(resultado.isPresent());
		assertEquals("João", resultado.get().getNome());
	}

	@Test
	public void testAtualizarPessoa() throws Exception {
		UUID id = UUID.randomUUID();
		Pessoa pessoaExistente = new Pessoa();
		pessoaExistente.setId(id);
		pessoaExistente.setNome("João");
		pessoaExistente.setDataNascimento(LocalDate.of(1990, 1, 1));

		Pessoa pessoaAtualizada = new Pessoa();
		pessoaAtualizada.setNome("João Atualizado");
		pessoaAtualizada.setDataNascimento(LocalDate.of(1990, 1, 1));

		when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaExistente));
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaAtualizada);

		Pessoa resultado = pessoaService.atualizarPessoa(id, pessoaAtualizada);
		assertNotNull(resultado);
		assertEquals("João Atualizado", resultado.getNome());
	}

	@Test
	public void testDeletarPessoa() {
		UUID id = UUID.randomUUID();

		when(pessoaRepository.existsById(id)).thenReturn(true);

		pessoaService.deletarPessoa(id);

		verify(pessoaRepository, times(1)).deleteById(id);
	}
}
