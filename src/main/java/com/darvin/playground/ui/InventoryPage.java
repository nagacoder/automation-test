package com.darvin.playground.ui;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {
    public static final Target TITLE = Target.the("page title").locatedBy("[data-test='title']");
    public static final Target INVENTORY_ITEMS = Target.the("inventory items").locatedBy(".inventory_item");
}
