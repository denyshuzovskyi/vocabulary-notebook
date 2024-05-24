package com.danny.vocabularynotebook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "words")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "word_seq_generator")
    @SequenceGenerator(name = "word_seq_generator", sequenceName = "words_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    private String word;

    private String pos;

    private String pronunciation;

    private String definition;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Example> examples;

    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    public void addExample(Example example) {
        if (examples == null) {
            examples = new ArrayList<>();
        }
        example.setWord(this);
        examples.add(example);
    }
}
