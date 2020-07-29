package org.ework.model;

import com.sun.tools.javac.util.StringUtils;

import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Value
public class Sentence {

    public static final String SPECIAL_CHARACTER = "\\W";

    private List<String> sentence;

    public Sentence(String[] words) {
        this.sentence = unmodifiableList(stream(words)
                .map(Sentence::reduceWhiteCharacters)
                .map(String::trim)
                .filter(StringUtils::isNotEmpty)
                .sorted((s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()))
                .collect(toList()));
    }


    private static String reduceWhiteCharacters(String src) {
        return src.replaceAll(SPECIAL_CHARACTER, EMPTY);
    }

}
