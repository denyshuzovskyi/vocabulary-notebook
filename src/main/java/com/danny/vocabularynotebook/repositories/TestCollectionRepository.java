package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.TestCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestCollectionRepository extends JpaRepository<TestCollection, Long> {
    @Query("SELECT tc FROM TestCollection tc WHERE tc.notebook.id = :notebookId ORDER BY tc.createdAt DESC")
    Optional<TestCollection> findLatestByNotebookId(@Param("notebookId") Long notebookId);

    @Query("SELECT COUNT(tc) > 0 FROM TestCollection tc WHERE tc.notebook.id = :notebookId")
    boolean existsForNotebookId(@Param("notebookId") Long notebookId);
}
