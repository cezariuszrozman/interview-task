package org.ework;


import org.ework.model.Text;
import org.ework.parser.ParseStrategy;
import org.ework.parser.impl.CsvParserStrategy;

public class Main {
    public static void main(String[] args) throws Exception {
        Text text = new Text("Mary had a little lamb. Peter called for the wolf, and Aesop came.\n" +
                "Cinderella likes shoes.");
      //  System.out.println("1");
       // text.getSentence().forEach(System.out::println);
       // System.out.println("2");
        text = new Text(" Mary   had a little  lamb  . \n" +
                "\n" +
                "\n" +
                "  Peter   called for the wolf   ,  and Aesop came .\n" +
                " Cinderella  likes shoes..");
       // text.getSentence().forEach(System.out::println);
        ParseStrategy csv = new CsvParserStrategy();
        csv.execute(System.in);
    }
}
