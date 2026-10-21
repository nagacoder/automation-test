package com.darvin.playground.sites.site2.ui;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {
    public static final Target ADD_BACKPACK = Target.the("add backpack to cart button")
            .locatedBy("[data-test='add-to-cart-sauce-labs-backpack']");
    public static final Target CART_LINK = Target.the("cart link").locatedBy("[data-test='shopping-cart-link']");
}
