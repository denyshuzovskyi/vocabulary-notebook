package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSessionRepository extends JpaRepository<TestSession, Long> {
}
