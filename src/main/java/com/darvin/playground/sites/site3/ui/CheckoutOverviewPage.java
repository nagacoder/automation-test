package com.darvin.playground.sites.site3.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutOverviewPage {
    public static final Target ITEM_NAME = Target.the("checkout item name").locatedBy(".cart_item .inventory_item_name");
}
