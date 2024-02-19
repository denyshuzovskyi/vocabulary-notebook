package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.TestCollection;
import com.danny.vocabularynotebook.entities.TestCollectionGenerationJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCollectionGenerationJobRepository extends JpaRepository<TestCollectionGenerationJob, Long> {
}
