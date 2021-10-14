package com.mohdev;

import java.util.ArrayList;
import java.util.List;

public class HtmlTemplate {
    private final List<HtmlElement> treeNodes;
    private final StringBuffer outputTemplate;
    private final HeadTag headTag;
    private final Body body;

    private HtmlTemplate() {
        treeNodes = new ArrayList<>();
        outputTemplate = new StringBuffer();
        setup();
        headTag = HeadTag.make();
        body = Body.make();
        treeNodes.add(headTag);
        treeNodes.add(body);
    }

    private void setup() {
        outputTemplate.append("<!DOCTYPE html>\n");
        outputTemplate.append("<html>\n");
    }

    public static HtmlTemplate make() {
        return new HtmlTemplate();
    }

    public HtmlElement addToBody(HtmlElement htmlElement) {
        body.push(htmlElement);
        return htmlElement;
    }

    public void addTopLevelTag(TopLStructureTag topLevelTag) {
        if (topLevelTag instanceof Body || topLevelTag instanceof HeadTag) {
            System.out.println("Exit: As far as I know, There's one body tag and head tag in HTML document. That's provided already.");
            return;
        }
        treeNodes.add((HtmlElement) topLevelTag); // TODO: For now this works fine, Assuming all topLevel objects will inherit from HtmlElement as they are tag at the end :)!
    }

    public void generateTemplate(boolean toFile) {
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
        endofGenerating(toFile);// if any clean up validating before serializing to file.html back to user;
    }

    private void endofGenerating(boolean toFile) {
        outputTemplate.append("</html>\n");
    }

    // public void addInlineStyle(HtmlElement tag, String cssValue);
    public void addAttrsBody(Attr... attributes) {
        body.addAttrs(attributes);
    }
}