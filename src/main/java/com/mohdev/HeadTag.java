package com.mohdev;

import java.util.ArrayList;
import java.util.List;

public class HeadTag extends StructuralElem implements TopLStructureTag {

    final List<HtmlElement> elements;

    private HeadTag() {
        elements = new ArrayList<>();
    }


    public static HeadTag make() {
        return new HeadTag();
    }

    public void push(HtmlElement htmlElement) {
        elements.add(htmlElement);
    }

    public void acceptTags(List<HtmlElement> tags) {
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
