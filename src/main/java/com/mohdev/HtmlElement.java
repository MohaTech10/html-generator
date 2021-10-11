package com.mohdev;

// TODO: Bad design, Very rigid, Everything depends on __inheritance__

import java.util.ArrayList;
import java.util.List;

// We should also classify attribute and map to which tags
abstract class Attribute {

    public abstract String represent(HtmlElement htmlElement);

}

class JSReference {

}

class ScriptBlock {
    JSReference jsReference;

    // public String callName;
}

class ScriptableAttr extends Attribute {
    final ScriptBlock  function = null;// FIXME

    void executeJS() {
        // Execute/call/link block
    }

    // TODO: Parameter: An attribute is set to a tag, Yes! However the scope of the project can get rid of this injection by -ignoreValid flag , i.e, User responsible for such input
    @Override
    public String represent(HtmlElement htmlElement) {
        return " online=\"" + "scriptFunction()" + ";\"";
    }
}


class StyleAttr extends Attribute {
    public String value; // FIXME: StyleAttr has property:value;

    public StyleAttr() {
    }

    public void execute() {
        // TODO: Use REGEX to make sure they're in the right form, i.e,. property: value. And if there's more than one pair make sure separating with `;'
    }

    public String represent(HtmlElement htmlElement) {
        return " style=\"" + value + ";\"";
    }
}

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
    public void addAttrsBody(Attribute... attributes) {
        body.addAttrs(attributes);
    }
}

public abstract class HtmlElement {
    public abstract void generate(StringBuffer buffer);
    public abstract void setAttributes(Attribute... attrs);
}

// A group of tags under structural elements
class StructuralElem extends HtmlElement {

    @Override
    public void generate(StringBuffer buffer) {

    }

    @Override
    public void setAttributes(Attribute... attrs) {

    }
}

class TextTag extends HtmlElement {
    @Override
    public void generate(StringBuffer buffer) {

    }

    @Override
    public void setAttributes(Attribute... attrs) {

    }
}

class HeadTag extends StructuralElem implements TopLStructureTag {

    final List<HtmlElement> elements;

    private HeadTag() {
        elements = new ArrayList<>();
    }


    public static HeadTag make() {
        return new HeadTag();
    }

    public void push(HtmlElement htmlElement) {
        elements.add(htmlElement);
    }

    public void acceptTags(List<HtmlElement> tags) {
        elements.addAll(tags);
    }

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
    final List<HtmlElement> elements;
    final List<Attribute> attributes;
    final StyleAttr styleAttr = new StyleAttr();  // Default

    private Body() {
        elements = new ArrayList<>();
        attributes = new ArrayList<>();
    }

    public static Body make() {
        return new Body();
    }

    public void push(HtmlElement htmlElement) {
        elements.add(htmlElement);
    }

    public void acceptTags(List<HtmlElement> tags) {
        elements.addAll(tags);
    }

    //    public void addAttribute()
    public void addAttr(Attribute attribute) {
        attributes.add(attribute);
    }

    public void addAttrs(Attribute[] attrs) {
        attributes.addAll(List.of(attrs));
    }

    @Override
    public void generate(StringBuffer buffer) {
        buffer.append("<body");
        for (var attr : attributes)
            buffer.append(attr.represent(this));
        // attr.execute.tag.repAppend(" stype
        buffer.append(">\n");
        for (var nestedTag : elements) {
            System.out.println("Getting called?");
            nestedTag.generate(buffer);
        }
    }

    public static void main(String[] args) {
        var template = HtmlTemplate.make();
        template.addAttrsBody(new ScriptableAttr());
        template.addToBody(Paragraph.make("myContent")).setAttributes(new StyleAttr()); // FIXME: Use Builder pattern. ..().buildAttr(..)
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
    private final List<Attribute> attributes;
    private Paragraph(String content) {
        // TODO: move logic out of standard constructor
        this.content = content;
        attributes = new ArrayList<>();
    }

    public static Paragraph make(String content) {
        return new Paragraph(content);
    }

    @Override
    public void setAttributes(Attribute... attrs) {
        attributes.addAll(List.of(attrs));
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