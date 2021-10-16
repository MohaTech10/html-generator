package com.mohdev.runner;

import com.mohdev.tags.*;
import com.mohdev.attributes.Attr;

public class Driver {

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(Attr.make("online", "js()"));
        template.addToBody(ParagraphTag.make("myContent"))
                .setAttributes(Attr.make("style", "color:red;"));
        // Use data structure and so queue, linked list, and pointer
        template.addToBody(ImageTag.make(Attr.make("src", "path")));
        var formTag = FormTag.make();
        // TODO: Create composition Option
        template.addToBody(formTag);
        formTag.push(InputTag.make(Attr.make("type", "text")));

        // make should return calls to make other types;

        template.toFile();
    }
}
