package com.mohdev.tags;


import com.mohdev.attributes.Attr;

public class HrTag extends HtmlTag implements UnpairedTag {

    @Override
    protected void generate(StringBuffer buffer) {
        buffer.append("<hr/>");
    }

    @Override
    public void setAttributes(Attr... attrs) {
    }
}
