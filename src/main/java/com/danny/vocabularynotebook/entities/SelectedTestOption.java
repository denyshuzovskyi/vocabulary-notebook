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
@Table(name = "selected_test_options")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class SelectedTestOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "selected_test_option_seq_generator")
    @SequenceGenerator(name = "selected_test_option_seq_generator", sequenceName = "selected_test_options_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_option_id")
    private TestOption selectedOption;

    @ManyToOne
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;
}
