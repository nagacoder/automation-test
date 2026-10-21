package com.darvin.playground.sites.site3.ui;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {
    public static final Target ADD_BACKPACK = Target.the("add backpack to cart button")
            .locatedBy("//div[@class='inventory_item'][.//div[text()='Sauce Labs Backpack']]//button");
    public static final Target CART_LINK = Target.the("cart link").locatedBy(".shopping_cart_link");
}
