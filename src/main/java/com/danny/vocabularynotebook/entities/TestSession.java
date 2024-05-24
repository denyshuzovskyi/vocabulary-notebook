package com.danny.vocabularynotebook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "test_sessions")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TestSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test_session_seq_generator")
    @SequenceGenerator(name = "test_session_seq_generator", sequenceName = "test_sessions_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_collection_id")
    private TestCollection testCollection;

    @OneToMany(mappedBy = "testSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestResult> testResults;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    public void addTestResult(TestResult testResult) {
        if (Objects.isNull(testResults)) {
            testResults = new ArrayList<>();
        }
        testResult.setTestSession(this);
        testResults.add(testResult);
    }
}
