package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

public class InputTag extends HtmlTag {


    private final static String[] VALID_INPUT_ATTR_TYPE = { "text", "password", "submit" };
    private final Attr type; // Non-optional
    private final List<Attr> attributes;

    private InputTag(Attr type) {
        this.type = type;
        attributes = new ArrayList<>();
        attributes.add(type);
    }

    // // foo
    public static InputTag make(Attr type) {
        // FIXME: GraphValidator & Assuming attr.key string == "type"
        var valid = false;
        for (String possibleValue : VALID_INPUT_ATTR_TYPE) {
            if (type.getValue().equals(possibleValue)) { valid = true; break;}
        }
        if (!valid) System.out.println("Not a valid value for type attribute");
        return new InputTag(type);
    }

    @Override
    protected void generate(StringBuffer buffer) {
        buffer.append("<input");
        for (var attr : attributes) {
            buffer.append(attr.represent(this));
        }
        buffer.append("/>\n");
    }

    @Override
    public void setAttributes(Attr... attrs) {

    }
}
