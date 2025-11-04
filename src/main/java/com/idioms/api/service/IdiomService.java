package com.idioms.api.service;

import com.idioms.api.model.Idiom;
import com.idioms.api.repository.IdiomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer that handles business logic for Idioms.
 * Delegates data operations to the repository and performs input validation.
 */
@Service
public class IdiomService {

    private final IdiomRepository idiomRepo;

    public IdiomService(IdiomRepository idiomRepo) {
        this.idiomRepo = idiomRepo;
    }

    /**
     * Returns a list of random idioms filtered by frequency.
     */
    public List<Idiom> findRandomIdioms(int frequency, int count) {
        return idiomRepo.randomIdioms(frequency, count);
    }

    /**
     * Searches idioms by keyword (text, meaning, or example).
     * Throws an IllegalArgumentException if the query is null or too short.
     */
    public List<Idiom> searchIdioms(String query) {
        if (query == null || query.trim().length() < 3) {
            throw new IllegalArgumentException("Search term too short (min. 3 characters).");
        }

        String cleanQuery = query.trim();
        return idiomRepo.search(cleanQuery);
    }
}
