package com.mohdev.tags;

import java.util.ArrayList;
import java.util.List;

public class HeadTag extends StructuralTag implements TopLStructureTag {

    final List<HtmlTag> elements;

    private HeadTag() {
        elements = new ArrayList<>();
    }


    public static HeadTag make() {
        return new HeadTag();
    }

    public void push(HtmlTag htmlTag) {
        elements.add(htmlTag);
    }

    public void acceptTags(List<HtmlTag> tags) {
        elements.addAll(tags);
    }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<head>");
    }

    @Override
    public void closeOff(StringBuffer buffer) {
        buffer.append("\n</head>\n");
    }
}
