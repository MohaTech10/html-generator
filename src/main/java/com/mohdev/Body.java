package com.mohdev;

import java.util.ArrayList;
import java.util.List;

public class Body extends StructuralElem implements TopLStructureTag {
    final List<HtmlElement> elements;
    final List<Attr> attributes;

    private Body() {
        elements = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    public static Body make() {
        return new Body();
    }

    public void push(HtmlElement htmlElement) {
        elements.add(htmlElement);
    }

    public void acceptTags(List<HtmlElement> tags) {
        elements.addAll(tags);
    }

    public void addAttrs(Attr[] attrs) {
        attributes.addAll(List.of(attrs));
    }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<body");
        for (var attr : attributes) {
            buffer.append(attr.represent(this));
        }
        // attr.execute.tag.repAppend(" stype
        buffer.append(">\n");
        for (var nestedTag : elements) {
            System.out.println("Getting called?");
            nestedTag.generate(buffer);
        }
    }

    @Override
    public void closeOff(StringBuffer buffer) {
        buffer.append("\n</body>\n");

    }
}
