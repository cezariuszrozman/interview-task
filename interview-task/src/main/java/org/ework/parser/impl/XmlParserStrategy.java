package org.ework.parser.impl;


import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import org.ework.model.Sentence;
import org.ework.parser.ParseStrategy;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.InputStream;

public class XmlParserStrategy implements ParseStrategy {
    @Override
    public String getName() {
        return "XML";
    }

    @Override
    public void execute(InputStream in) {

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        try (FileOutputStream fileOutputStream = new FileOutputStream("out.xml")) {
            XMLStreamWriter defaultWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream, "UTF-8");
            IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(defaultWriter);
            writer.setIndentStep("  ");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("text");
            while (in.available() != 0) {
                Sentence sentence = new Sentence(getSentence(in).trim().split("\\s+"));
                if (!sentence.getSentence().isEmpty()) {
                    writer.writeStartElement("sentence");
                    for (String word : sentence.getSentence()) {
                        writer.writeStartElement("word");
                        writer.writeCharacters(word);
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (Exception exc) {
            throw new IllegalStateException(exc);
        }
    }
}
