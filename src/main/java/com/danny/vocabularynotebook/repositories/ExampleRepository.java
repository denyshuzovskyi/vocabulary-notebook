package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
