package com.idioms.api.loader;

import com.idioms.api.model.Idiom;
import com.idioms.api.repository.IdiomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DataLoader implements CommandLineRunner {

    private final IdiomRepository idiomRepository;

    public DataLoader(IdiomRepository idiomRepository) {
        this.idiomRepository = idiomRepository;
    }

    @Override
    public void run(String... args) {
        if (idiomRepository.count() == 0) {
            importIdioms();
            System.out.println("Idioms imported successfully (initial setup).");
        } else {
            System.out.println("Database already populated — skipping import.");
        }
    }

    /**
     * Reads the idioms CSV file and saves each entry to the database.
     */
    private void importIdioms() {
        List<String[]> allLines;
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/data/idioms.csv"))) {
            allLines = lines
                    .filter(line -> !line.isBlank())
                    .map(line -> line.split("(?<=[^\\s]),(?=[^\\s])", -1)) // keep empty columns
                    .filter(cols -> cols.length >= 4)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read idioms CSV file", e);
        }

        for (String[] line : allLines) {
            Idiom idiom = new Idiom();
            idiom.setText(line[0]);
            idiom.setMeaning(line[1]);
            idiom.setExample(line[2]);
            idiom.setFrequency(parseFrequency(line[3]));
            idiomRepository.save(idiom);
        }
    }

    /**
     * Cleans and converts the frequency field from the CSV into an integer value.
     * If invalid, returns 0.
     */
    private int parseFrequency(String raw) {
        try {
            String cleaned = raw.replaceAll("[^0-9-]", "").trim();
            return cleaned.isEmpty() ? 0 : Integer.parseInt(cleaned);
        } catch (NumberFormatException e) {
            System.err.println("Invalid frequency value: '" + raw + "', defaulting to 0.");
            return 0;
        }
    }
}
