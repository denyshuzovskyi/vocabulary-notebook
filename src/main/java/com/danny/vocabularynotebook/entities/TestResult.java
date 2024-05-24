package com.danny.vocabularynotebook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "test_results",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"test_session_id", "test_id"})
        }
)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test_result_seq_generator")
    @SequenceGenerator(name = "test_result_seq_generator", sequenceName = "test_results_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_session_id")
    private TestSession testSession;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectedTestOption> selectedTestOptions;

    public void addSelectedTestOption(SelectedTestOption selectedTestOption) {
        if (Objects.isNull(selectedTestOptions)) {
            selectedTestOptions = new ArrayList<>();
        }
        selectedTestOption.setTestResult(this);
        selectedTestOptions.add(selectedTestOption);
    }
}