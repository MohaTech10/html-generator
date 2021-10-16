package com.mohdev.attributes;

import com.mohdev.tags.HtmlTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This should validate either one of these cases, Only one:
/*
    1- The attribute belongs to the set of elements. See html standard;
    2- the attribute actually true, i.e, unknown attribute names should be removed.
    3- if we have an appropriate attribute name!, what about its value! e.g, style="disabled". attrs has set of valid values. See html standard
 */
public class GraphValidator {

    private enum Result {
        Valid, Invalid, // TODO: InvalidValue;
    }

    final private Map<Attr, List<HtmlTag>> validAttrs;
    final private List<HtmlTag> elements;

    private GraphValidator() {
        validAttrs = new HashMap<>();
        elements = new ArrayList<>();
    }

    // See usages of system entity Attr, where you can call sub-entity services
    public Result lookupGraph(Attr attr, HtmlTag element) {
        // TODO: See weather we have a valid attribute name already

        // Assume: Attr name is valid
        var elements = validAttrs.get(attr);
        for (HtmlTag htmlTag : elements) {
            if (htmlTag.equals(element))
                return Result.Valid;  // This attr belongs to the passed element param
        }

        // TODO: Check value validation & move logic from here.

        return Result.Invalid;

    }

    public static GraphValidator make() {
        return new GraphValidator();
    }
}
