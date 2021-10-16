package com.mohdev.tags;

//// We should also classify attribute and map to which tags
//abstract class Attribute {
//
//    public abstract String represent(HtmlElement htmlElement);
//
//}
//
//class JSReference {
//
//}
//
//class ScriptBlock {
//    JSReference jsReference;
//
//    // public String callName;
//}
//
//class ScriptableAttr extends Attribute {
//    final ScriptBlock  function = null;// FIXME
//
//    void executeJS() {
//        // Execute/call/link block
//    }
//
//    // TODO: Parameter: An attribute is set to a tag, Yes! However the scope of the project can get rid of this injection by -ignoreValid flag , i.e, User responsible for such input
//    @Override
//    public String represent(HtmlElement htmlElement) {
//        return " online=\"" + "scriptFunction()" + ";\"";
//    }
//}
//
//
//class StyleAttr extends Attribute {
//    public String value; // FIXME: StyleAttr has property:value;
//
//    public StyleAttr() {
//    }
//
//    public void execute() {
//        // TODO: Use REGEX to make sure they're in the right form, i.e,. property: value. And if there's more than one pair make sure separating with `;'
//    }
//
//    public String represent(HtmlElement htmlElement) {
//        return " style=\"" + value + ";\"";
//    }
//}

import com.mohdev.attributes.Attr;

// Each element/tag has a list of valid tags.
public abstract class HtmlTag {
    protected abstract void generate(StringBuffer buffer);

    public abstract void setAttributes(Attr... attrs);
}