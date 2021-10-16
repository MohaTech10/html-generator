package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class ParagraphTag extends TextTag {
    private final String content;
    private final List<Attr> attributes;

    private ParagraphTag(String content) {
        // TODO: move logic out of standard constructor
        this.content = content;
        attributes = new ArrayList<>();
    }

    public static ParagraphTag make(String content) {
        return new ParagraphTag(content);
    }

    @Override
    public void setAttributes(Attr... attrs) {
        // Call sites: those who have access the real environment entity, the sys objects. Can call these services thats composed of detailed version of the entit
        attributes.addAll(List.of(attrs));
    }

    @Override
    public String toString() {
        return "<p> " + content + " </p>";
    }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<p");
        for (var attr : attributes) {
            buffer.append(attr.represent(this));
        }
        buffer.append(">\n").append(content).append("\n</p>\n");
    }
}
