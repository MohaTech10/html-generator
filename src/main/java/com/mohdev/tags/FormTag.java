package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

// FIXME: Check, Can every html tag contain other children tags?
// If yes: HtmlTag's protocol should define push, push, elements. As shared property
public class FormTag extends HtmlTag {

    // in HTML5 both action and method are optional attributes .
    private final List</*FormElement*/ HtmlTag> elements;
    private final List<Attr> attributes;


    private FormTag() {
        elements = new ArrayList<>();
        attributes = new ArrayList<>();
    }
    public static FormTag make() {
        return new FormTag();
    }

    public void push(HtmlTag htmlTag) { elements.add(htmlTag); }

    public void acceptTags(List<HtmlTag> tags) {
        elements.addAll(tags);
    }



    // FIXME Already this looks messy! move to TagFormatter
    @Override
    protected void generate(StringBuffer buffer) {
        // Paired tag
        buffer.append("<form");
        for (var attr : attributes) {
            buffer.append(attr.represent(this));
        }

        buffer.append(">\n");
        for (var nestedTag : elements) {
            nestedTag.generate(buffer);
        }
        buffer.append("\n</form>\n");
    }

    @Override
    public void setAttributes(Attr... attrs) {

    }
}
