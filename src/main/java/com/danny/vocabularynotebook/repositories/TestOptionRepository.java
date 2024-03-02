package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.TestOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestOptionRepository extends JpaRepository<TestOption, Long> {
    @Query("SELECT COUNT(*) " +
            "FROM TestOption to " +
            "JOIN to.test t " +
            "WHERE t.testCollection.id = :testCollectionId AND to.isCorrect = true")
    long getNumberOfCorrectTestOptionsInTestCollection(@Param("testCollectionId") Long testCollectionId);
}
