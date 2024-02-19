package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.Test;
import com.danny.vocabularynotebook.entities.TestCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query("SELECT t FROM Test t LEFT JOIN FETCH t.testOptions WHERE t.testCollection.id = :testCollectionId")
    List<Test> findAllWithTestOptionsByTestCollectionId(@Param("testCollectionId") Long testCollectionId);
}
