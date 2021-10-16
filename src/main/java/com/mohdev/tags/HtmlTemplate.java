package com.mohdev.tags;

import com.mohdev.attributes.Attr;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtmlTemplate {
    private final List<HtmlTag> treeNodes;
    private final StringBuffer outputTemplate;
    private final HeadTag headTag;
    private final BodyTag bodyTag;
    // modification property to control generating problem

    private HtmlTemplate() {
        treeNodes = new ArrayList<>();
        outputTemplate = new StringBuffer();
        setup();
        headTag = HeadTag.make();
        bodyTag = BodyTag.make();
        treeNodes.add(headTag);
        treeNodes.add(bodyTag);
    }

    private void setup() {
        outputTemplate.append("<!DOCTYPE html>\n");
        outputTemplate.append("<html>\n");
    }

    public static HtmlTemplate make() {
        return new HtmlTemplate();
    }

    public HtmlTag addToBody(HtmlTag htmlTag) {
        bodyTag.push(htmlTag);
        return htmlTag;
    }

    public void addTopLevelTag(TopLStructureTag topLevelTag) {
        if (topLevelTag instanceof BodyTag || topLevelTag instanceof HeadTag) {
            System.out.println("Exit: As far as I know, There's one body tag and head tag in HTML document. That's provided already.");
            return;
        }
        treeNodes.add((HtmlTag) topLevelTag); // TODO: For now this works fine, Assuming all topLevel objects will inherit from HtmlElement as they are tag at the end :)!
    }

    private void generateTemplate() {
        TopLStructureTag current = null;
        // TODO: Improve by testing linkedList or Stack to keep track of topLevel tags and close them
        for (var tag : treeNodes) {
            if (tag instanceof TopLStructureTag) {
                if (current != null)
                    current.closeOff(outputTemplate);
                current = (TopLStructureTag) tag;
            }
            tag.generate(outputTemplate);
        }
        assert current != null;
        current.closeOff(outputTemplate);
        outputTemplate.append("</html>\n");
    }


    // Should create file.html corresponding to template created so far.
    // TODO: Change design of toFile service to better. once this code is called we should validate it
    public void toFile() {
        // Move to class FileGenerator to keep track of number of new nodes, content of new nodes and to serve as matcher;
        generateTemplate();
        // Validate();
        var file = Paths.get("index.html");
        try {
            Files.write(file, Collections.singleton(outputTemplate), StandardCharsets.UTF_8);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // public void addInlineStyle(HtmlElement tag, String cssValue);
    public void addAttrsBody(Attr... attributes) {
        bodyTag.addAttrs(attributes);
    }
}