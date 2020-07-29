package org.ework.model;

import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;


@Value
public final class Text {


    private List<Sentence> sentence;

    public Text(String sentence) {
        this.sentence = unmodifiableList(stream(sentence.split("\\."))
                .map(String::trim)
                .filter(StringUtils::isNoneEmpty)
                .map(s -> new Sentence(s.split("\\s+")))
                .collect(toList()));
    }


}
