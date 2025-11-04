package com.idioms.api.repository;

import com.idioms.api.model.Idiom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Idiom entities.
 * Provides basic CRUD operations and two custom query methods:
 *  - Full-text search (JPQL)
 *  - Random selection by frequency (native SQL)
 */
@Repository
public interface IdiomRepository extends JpaRepository<Idiom, Long> {

    /**
     * Performs a case-insensitive search for idioms by text, meaning, or example.
     */
    @Query("""
        SELECT i FROM Idiom i
        WHERE LOWER(i.text) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(i.meaning) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(i.example) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<Idiom> search(@Param("query") String query);

    /**
     * Returns a list of random idioms filtered by frequency.
     * Uses a native SQL query for performance and database-level randomization.
     */
    @Query(
        value = "SELECT * FROM idioms WHERE frequency = :frequency ORDER BY RANDOM() LIMIT :count",
        nativeQuery = true
    )
    List<Idiom> randomIdioms(@Param("frequency") int frequency, @Param("count") int count);
}
