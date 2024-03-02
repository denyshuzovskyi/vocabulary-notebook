package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.SelectedTestOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedTestOptionRepository extends JpaRepository<SelectedTestOption, Long> {
//    @Query("SELECT COUNT(*) " +
//            "FROM SelectedTestOption sto " +
//            "JOIN sto.selectedOption to " +
//            "JOIN to.test t " +
//            "WHERE t.testCollection.id = :testCollectionId AND to.isCorrect = true")
//    long getNumberOfCorrectSelectedTestOptionsInTestCollection(@Param("testCollectionId") Long testCollectionId);

    @Query("SELECT COUNT(*) " +
            "FROM SelectedTestOption sto " +
            "JOIN sto.testResult tr " +
            "JOIN sto.selectedOption to " +
            "WHERE tr.testSession.id = :testSessionId AND to.isCorrect = true")
    long getNumberOfCorrectSelectedTestOptionsInTestSession(@Param("testSessionId") Long testSessionId);
}
