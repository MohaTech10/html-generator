package com.mohdev.tags;

import com.mohdev.attributes.Attr;

// Make validator's job easy
public class ImageTag extends HtmlTag implements UnpairedTag {
    private final String src;

    private ImageTag(String path) {
        this.src = path;
    }

    public static ImageTag make(String path) {
        return new ImageTag(path);
    }
    @Override
    protected void generate(StringBuffer buffer) {
        buffer.append("<img src=").append(src).append(" />");
    }

    @Override
    public void setAttributes(Attr... attrs) {
        // Optionals are optional
    }
}
