package org.ework.parser.impl;

import org.ework.parser.ParseStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import static java.nio.charset.Charset.forName;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.io.IOUtils.toInputStream;


public class XmlParserStrategyTest {

  @Test
  public void shouldConvertToXml() throws Exception {
    InputStream stubInputStream =
        toInputStream(" Mary   had a little  lamb  . \n" +
            "\n" +
            "\n" +
            "  Peter   called for the wolf   ,  and Aesop came .\n" +
            " Cinderella  likes shoes..", "UTF-8");

    ParseStrategy xmlStrategy = new XmlParserStrategy();
    xmlStrategy.execute(stubInputStream);

    String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<text>\n" +
        "  <sentence>\n" +
        "    <word>a</word>\n" +
        "    <word>had</word>\n" +
        "    <word>lamb</word>\n" +
        "    <word>little</word>\n" +
        "    <word>Mary</word>\n" +
        "  </sentence>\n" +
        "  <sentence>\n" +
        "    <word>Aesop</word>\n" +
        "    <word>and</word>\n" +
        "    <word>called</word>\n" +
        "    <word>came</word>\n" +
        "    <word>for</word>\n" +
        "    <word>Peter</word>\n" +
        "    <word>the</word>\n" +
        "    <word>wolf</word>\n" +
        "  </sentence>\n" +
        "  <sentence>\n" +
        "    <word>Cinderella</word>\n" +
        "    <word>likes</word>\n" +
        "    <word>shoes</word>\n" +
        "  </sentence>\n" +
        "</text>";

    Assert.assertTrue(new File("out.xml").exists());
    String content = Files.readAllLines(get("out.xml"), forName("UTF-8")).stream().collect(joining("\n"));
    Assert.assertEquals(expected, content);
  }
}