package com.danny.vocabularynotebook.entities;

import jakarta.persistence.Column;
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
@Table(name = "test_options")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TestOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test_option_seq_generator")
    @SequenceGenerator(name = "test_option_seq_generator", sequenceName = "test_options_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    private String option;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
}
