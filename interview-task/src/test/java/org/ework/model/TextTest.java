package org.ework.model;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


public class TextTest {

  @Test
  public void shouldCreateCorrectTextWithSentences_1() {
    //given
    String src = "Mary had a little lamb. Peter called for the wolf, and Aesop came.\n" +
        "Cinderella likes shoes.";
    //when
    Text text = new Text(src);
    //then
    assertEquals(3, text.getSentence().size());
    assertEquals(asList("a", "had", "lamb", "little", "Mary"), text.getSentence().get(0).getSentence());
    assertEquals(asList("Aesop", "and", "called", "came", "for", "Peter", "the", "wolf"), text.getSentence().get(1).getSentence());
    assertEquals(asList("Cinderella", "likes", "shoes"), text.getSentence().get(2).getSentence());
  }

  @Test
  public void shouldCreateCorrectTextWithSentences_2() {
    //given
    String src = " Mary   had a little  lamb  . \n" +
        "\n" +
        "\n" +
        "  Peter   called for the wolf   ,  and Aesop came .\n" +
        " Cinderella  likes shoes..";
    //when
    Text text = new Text(src);
    //then
    assertEquals(3, text.getSentence().size());
    assertEquals(asList("a", "had", "lamb", "little", "Mary"), text.getSentence().get(0).getSentence());
    assertEquals(asList("Aesop", "and", "called", "came", "for", "Peter", "the", "wolf"), text.getSentence().get(1).getSentence());
    assertEquals(asList("Cinderella", "likes", "shoes"), text.getSentence().get(2).getSentence());
  }

}