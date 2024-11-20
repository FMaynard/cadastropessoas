package br.com.apolinho.cadastropessoas.controller;

import br.com.apolinho.cadastropessoas.model.Pessoa;
import br.com.apolinho.cadastropessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable UUID id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable UUID id, @RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(id, pessoa);
            return ResponseEntity.ok(pessoaAtualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable UUID id) {
        try {
            pessoaService.deletarPessoa(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
