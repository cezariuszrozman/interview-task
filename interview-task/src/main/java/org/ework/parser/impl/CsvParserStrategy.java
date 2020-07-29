package org.ework.parser.impl;


import org.ework.model.Sentence;
import org.ework.parser.ParseStrategy;

import java.io.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.io.IOUtils.copy;

public class CsvParserStrategy implements ParseStrategy {

    private static final String TMP_FILE = "out_noheader.csv";
    private static final String RESULT_FILE = "out.csv";

    @Override
    public String getName() {
        return "CSV";
    }

    @Override
    public void execute(InputStream in) {
        int maxWords = 0;
        try (BufferedWriter out = new BufferedWriter(new FileWriter(TMP_FILE))) {
            int index = 1;
            while (in.available() != 0) {
                Sentence sentence = new Sentence(getSentence(in).trim().split("\\s+"));
                if (!sentence.getSentence().isEmpty()) {
                    out.write("Sentence " + (index++) + ", " + sentence.getSentence().stream().collect(joining(", ")));
                    out.newLine();
                    out.flush();
                    if (sentence.getSentence().size() > maxWords) {
                        maxWords = sentence.getSentence().size();
                    }
                }
            }
        } catch (IOException exc) {
            throw new IllegalStateException(exc);
        }
        addHeaders(maxWords);
    }

    private void addHeaders(int maxWords) {
        String headerRow = IntStream.rangeClosed(1, maxWords)
                .boxed().map(i -> "Word " + i).collect(joining(", "));
        try (BufferedWriter out = new BufferedWriter(new FileWriter(RESULT_FILE));
             BufferedReader in = new BufferedReader(new FileReader(TMP_FILE))) {
            out.write(", " + headerRow);
            out.newLine();
            copy(in, out);
        } catch (IOException exc) {
            throw new IllegalStateException(exc);
        }
    }
}
