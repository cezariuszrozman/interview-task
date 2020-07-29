package org.ework.parser.impl;

import org.ework.parser.ParseStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static java.nio.charset.Charset.forName;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.io.IOUtils.toInputStream;

public class CsvParserStrategyTest {

    @Test
    public void shouldConvertToCsv() throws IOException {
        InputStream stubInputStream =
                toInputStream(" Mary   had a little  lamb  . \n" +
                        "\n" +
                        "\n" +
                        "  Peter   called for the wolf   ,  and Aesop came .\n" +
                        " Cinderella  likes shoes..", "UTF-8");

        ParseStrategy csvStrategy = new CsvParserStrategy();
        csvStrategy.execute(stubInputStream);

        String expected = ", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8\n" +
                "Sentence 1, a, had, lamb, little, Mary\n" +
                "Sentence 2, Aesop, and, called, came, for, Peter, the, wolf\n" +
                "Sentence 3, Cinderella, likes, shoes";

        Assert.assertTrue(new File("out.csv").exists());
        String content = Files.readAllLines(get("out.csv"), forName("UTF-8")).stream().collect(joining("\n"));
        Assert.assertEquals(expected, content);
    }
}