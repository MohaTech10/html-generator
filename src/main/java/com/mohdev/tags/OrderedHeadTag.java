package com.mohdev.tags;

// <h1...h6>
public class OrderedHeadTag extends TextTag {
    private final String orderNumber;

    private OrderedHeadTag(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public static OrderedHeadTag make(String orderNumber) {
        // For quick reference;
        var shouldBeParsed = Integer.parseInt(orderNumber);
        if (shouldBeParsed > 6 || shouldBeParsed < 1) System.out.println("Error!");
        return new OrderedHeadTag(orderNumber);
    }

}
