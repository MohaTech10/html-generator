package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class BodyTag extends StructuralTag implements TopLStructureTag {
    final List<HtmlTag> elements;
    final List<Attr> attributes;

    private BodyTag() {
        elements = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    public static BodyTag make() {
        return new BodyTag();
    }

    public void push(HtmlTag htmlTag) {
        elements.add(htmlTag);
    }

    public void acceptTags(List<HtmlTag> tags) {
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
