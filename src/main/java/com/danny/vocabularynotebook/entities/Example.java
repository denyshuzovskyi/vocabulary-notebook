package com.danny.vocabularynotebook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "examples")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "example_seq_generator")
    @SequenceGenerator(name = "example_seq_generator", sequenceName = "examples_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    private String exampleText;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
}
