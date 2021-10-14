package com.mohdev;

public class Driver {

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(Attr.make("online", "js()"));
        template.addToBody(Paragraph.make("myContent"))
                .setAttributes(Attr.make("style", "color:red;"));

        template.toFile();
    }
}
