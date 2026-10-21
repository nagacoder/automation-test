package com.darvin.playground.sites.site3.tasks;

import com.darvin.playground.interactions.Paced;
import com.darvin.playground.sites.site3.ui.CartPage;
import com.darvin.playground.sites.site3.ui.CheckoutInfoPage;
import com.darvin.playground.sites.site3.ui.CheckoutOverviewPage;
import com.darvin.playground.sites.site3.ui.InventoryPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CheckoutBackpack {
    public static Performable upToTheOverview() {
        return Task.where("{0} checks out the backpack up to the overview",
                Paced.steps(
                        Click.on(InventoryPage.ADD_BACKPACK),
                        Click.on(InventoryPage.CART_LINK),
                        Click.on(CartPage.CHECKOUT),
                        Enter.theValue("Test").into(CheckoutInfoPage.FIRST_NAME),
                        Enter.theValue("User").into(CheckoutInfoPage.LAST_NAME),
                        Enter.theValue("12345").into(CheckoutInfoPage.POSTAL_CODE),
                        Click.on(CheckoutInfoPage.CONTINUE),
                        // Reached the destination once feature A is on screen
                        WaitUntil.the(CheckoutOverviewPage.ITEM_NAME, isVisible()).forNoMoreThan(15).seconds()
                ));
    }
}
