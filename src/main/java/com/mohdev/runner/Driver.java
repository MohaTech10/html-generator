package com.mohdev.runner;

import com.mohdev.tags.HtmlTemplate;
import com.mohdev.tags.Paragraph;
import com.mohdev.attributes.Attr;

public class Driver {

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(Attr.make("online", "js()"));
        template.addToBody(Paragraph.make("myContent"))
                .setAttributes(Attr.make("style", "color:red;"));

        template.toFile();
    }
}
