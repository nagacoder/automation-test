package com.darvin.playground.sites.site2.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {
    public static final Target ITEM_NAME = Target.the("cart item name").locatedBy("[data-test='inventory-item-name']");
}
