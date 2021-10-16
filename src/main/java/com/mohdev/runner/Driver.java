package com.mohdev.runner;

import com.mohdev.tags.HtmlTemplate;
import com.mohdev.tags.ImageTag;
import com.mohdev.tags.ParagraphTag;
import com.mohdev.attributes.Attr;

public class Driver {

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(Attr.make("online", "js()"));
        template.addToBody(ParagraphTag.make("myContent"))
                .setAttributes(Attr.make("style", "color:red;"));
        // Use data structure and so queue, linked list, and pointer
        template.addToBody(ImageTag.make("C:\\folder\\developer\\.png"));
        template.toFile();
    }
}
