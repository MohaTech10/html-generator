package com.mohdev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String represent(HtmlElement tag) {
        return " " + attrName + "=\"" + value + "\"";
    }
}

// This should validate either one of these cases, Only one:
/*
    1- The attribute belongs to the set of elements. See html standard;
    2- the attribute actually true, i.e, unknown attribute names should be removed.
    3- if we have an appropriate attribute name!, what about its value! e.g, style="disabled". attrs has set of valid values. See html standard
 */
class GraphValidator {

    private enum Result {
        Valid, Invalid, // TODO: InvalidValue;
    }
    final private Map<Attr, List<HtmlElement>> validAttrs;
    final private List<HtmlElement> elements;
    private GraphValidator() {
        validAttrs = new HashMap<>();
        elements = new ArrayList<>();
    }

    // See usages of system entity Attr, where you can call sub-entity services
    public Result lookupGraph(Attr attr, HtmlElement element) {
        // TODO: See weather we have a valid attribute name already

        // Assume: Attr name is valid
        var elements = validAttrs.get(attr);
        for (HtmlElement htmlElement : elements) {
            if (htmlElement.equals(element))
                return Result.Valid;  // This attr belongs to the passed element param
        }

        // TODO: Check value validation & move logic from here.

        return Result.Invalid;

    }

    public static GraphValidator make() { return new GraphValidator(); }
}
