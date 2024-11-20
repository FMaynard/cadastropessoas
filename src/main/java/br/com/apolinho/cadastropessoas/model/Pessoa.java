package br.com.apolinho.cadastropessoas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String cidadeOrigem;
    private String timeFutebol;
}
