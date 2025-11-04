package com.idioms.api.controller;

import com.idioms.api.model.Idiom;
import com.idioms.api.service.IdiomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idioms")
@Tag(name = "Idioms API", description = "REST endpoints for retrieving and searching idioms")
public class IdiomController {

    private final IdiomService idiomService;

    public IdiomController(IdiomService idiomService) {
        this.idiomService = idiomService;
    }

    /**
     * Returns random idioms from the database.
     * Optional parameters: frequency (default 5), count (default 10)
     */
    @GetMapping("/random")
    @Operation(summary = "Get random idioms", description = "Returns a list of random idioms, optionally filtered by frequency.")
    public List<Idiom> getRandom(
            @RequestParam(defaultValue = "5") int frequency,
            @RequestParam(defaultValue = "10") int count) {

        // Simple safeguard against extreme requests
        if (count > 100) count = 100;

        return idiomService.findRandomIdioms(frequency, count);
    }

    /**
     * Searches idioms by text, meaning, or example.
     */
    @GetMapping("/search")
    @Operation(summary = "Search idioms", description = "Searches idioms by text or meaning using a case-insensitive match.")
    public List<Idiom> search(@RequestParam String query) {
        return idiomService.searchIdioms(query);
    }
}
