package com.idioms.api.service;

import com.idioms.api.model.Idiom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for IdiomService.
 * Verifies repository interaction and basic business logic.
 */
@SpringBootTest
class IdiomServiceTest {

    @Autowired
    private IdiomService idiomService;

    @Test
    void randomIdioms_shouldReturnOnlyGivenFrequency() {
        // Arrange – test parameters
        int frequency = 5;
        int count = 3;

        // Act – execute service method
        List<Idiom> idioms = idiomService.findRandomIdioms(frequency, count);

        // Assert – validate result
        assertNotNull(idioms, "Result list must not be null");
        assertFalse(idioms.isEmpty(), "Result list should not be empty");
        assertTrue(idioms.size() <= count, "Result list should not exceed the requested count");
        assertTrue(idioms.stream().allMatch(i -> i.getFrequency() == frequency),
                "All idioms must match the requested frequency");
    }

    @Test
    void randomIdioms_shouldHandleInvalidFrequency() {
        // Arrange – use a frequency that might not exist
        int invalidFrequency = 0;
        int count = 5;

        // Act
        List<Idiom> idioms = idiomService.findRandomIdioms(invalidFrequency, count);

        // Assert – should not throw, just return an empty list
        assertNotNull(idioms, "Service should never return null");
    }
}
