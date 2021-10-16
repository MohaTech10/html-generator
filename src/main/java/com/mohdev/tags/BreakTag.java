package com.mohdev.tags;

import com.mohdev.attributes.Attr;

public class BreakTag extends HtmlTag implements UnpairedTag {

    @Override
    protected void generate(StringBuffer buffer) {
        buffer.append("<br/>");
    }

    @Override
    public void setAttributes(Attr... attrs) {

    }
}
