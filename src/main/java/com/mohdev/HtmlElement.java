package com.mohdev;

import java.util.ArrayList;
import java.util.List;

interface TopLStructureTag {
    void closeOff(StringBuffer buffer);
}
class HtmlTemplate {
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

    public static HtmlTemplate make() { return new HtmlTemplate(); }

    public void addToBody(HtmlElement htmlElement) { body.push(htmlElement); }

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

}
public abstract class HtmlElement {
    public abstract void generate(StringBuffer buffer);
}
// A group of tags under structural elements
class StructuralElem extends HtmlElement {

    @Override
    public void generate(StringBuffer buffer) {

    }
}
class TextTag extends HtmlElement {
    @Override
    public void generate(StringBuffer buffer) {

    }
}
class HeadTag extends StructuralElem implements TopLStructureTag {

    List<HtmlElement> elements;
    private HeadTag() {
        elements = new ArrayList<>();
    }


    public static HeadTag make() {
        return new HeadTag();
    }
    public void push(HtmlElement htmlElement) { elements.add(htmlElement); }
    public void acceptTags(List<HtmlElement> tags) { elements.addAll(tags); }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<head>");
    }

    @Override
    public void closeOff(StringBuffer buffer) {
        buffer.append("\n</head>\n");
    }
}

class Body extends StructuralElem implements TopLStructureTag {
    List<HtmlElement> elements;

    private Body() {
        elements = new ArrayList<>();
    }

    public static Body make() {
        return new Body();
    }
    public void push(HtmlElement htmlElement) { elements.add(htmlElement); }
    public void acceptTags(List<HtmlElement> tags) { elements.addAll(tags); }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<body>\n");
        for ( var nestedTag: elements) {
            System.out.println("Getting called?");
            nestedTag.generate(buffer);
        }

    }
    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addToBody(Paragraph.make("myContent"));
        template.generateTemplate(false);
        /*
        Result <!DOCTYPE html> <html> <head> </head> <body> <p> myContent </p> </body> </html>
        Cool for three lines of code
         */
    }

    @Override
    public void closeOff(StringBuffer buffer) {
        buffer.append("\n</body>\n");

    }
}

class Paragraph extends TextTag {
    private final String content;

    private Paragraph(String content) {
        // TODO: move logic out of standard constructor
        this.content = content;
    }

    public static Paragraph make(String content) {
        return new Paragraph(content);
    }

    @Override
    public String toString() {
        return "<p> " + content + " </p>";
    }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<p>").append(content).append("</p>");
    }
}