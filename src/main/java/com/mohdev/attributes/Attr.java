package com.mohdev.attributes;

import com.mohdev.tags.HtmlTag;

public class Attr/*TODO: Parameterized <Value>*/ {


    enum AttrKind {
        // We should have someway to recognize/distinguish jsValue;
        // Actually IDK how this will work.
        // disabled=boolean, type="string", JsBased=call(), style="comma-separated";
        BooleanAttr, StringBased, JsBased, CssBased;
    }
    final String attrName;
    final String value;

    private Attr(String attrName, String value) {
        this.attrName = attrName;
        this.value = value;
    }

    // TODO: Use other pattern
    public static Attr make(String attrName, String value) { return new Attr(attrName, value); }

    // TODO: pull out => Formatters job
    public String represent(HtmlTag tag) {
        return " " + attrName + "=\"" + value + "\"";
    }
}

