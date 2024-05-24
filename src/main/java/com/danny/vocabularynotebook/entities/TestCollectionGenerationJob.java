package com.danny.vocabularynotebook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "test_collection_generation_jobs")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class TestCollectionGenerationJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test_collection_generation_job_seq_generator")
    @SequenceGenerator(name = "test_collection_generation_job_seq_generator", sequenceName = "test_collection_generation_jobs_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne
    @JoinColumn(name = "test_collection_id")
    private TestCollection testCollection;

    @Enumerated(value = EnumType.STRING)
    private JobStatus status;

    public enum JobStatus {
        PENDING,
        RUNNING,
        COMPLETED,
        FAILED
    }
}
