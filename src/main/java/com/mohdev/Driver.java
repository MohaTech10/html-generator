package com.mohdev;

public class Driver {

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(Attr.make("online", "js()"));
        template.addToBody(Paragraph.make("myContent"))
                .setAttributes(Attr.make("type", "input"),
                        Attr.make("placeholder", "Enter name please...")); // FIXME: Use Builder pattern. ..().buildAttr(..)


        template.generateTemplate(false);
    }
}
