package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.util.ArrayList;
import java.util.List;

// Make validator's job easy
public class ImageTag extends HtmlTag implements UnpairedTag {
    private final Attr src;
    private final List<Attr> attributes;
    private ImageTag(Attr src) {
        this.src = src;
        attributes = new ArrayList<>();
        attributes.add(src);

    }

    public static ImageTag make(Attr src) {
        if (!src.getAttrName().equals("src")) System.out.println("You should provide a source or alter attribute");

        return new ImageTag(src);
    }

    @Override
    protected void generate(StringBuffer buffer) {
        buffer.append("<img");
        for (var attr : attributes) {
            buffer.append(attr.represent(this));
        }
        buffer.append("/>\n");
    }

    @Override
    public void setAttributes(Attr... attrs) {
        // Optionals are optional
    }
}
