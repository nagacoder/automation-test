package com.darvin.playground.sites.site2.tasks;

import com.darvin.playground.interactions.Paced;
import com.darvin.playground.sites.site2.ui.CartPage;
import com.darvin.playground.sites.site2.ui.InventoryPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenCartWithBackpack {
    public static Performable added() {
        return Task.where("{0} adds the backpack and opens the cart",
                Paced.steps(
                        Click.on(InventoryPage.ADD_BACKPACK),
                        Click.on(InventoryPage.CART_LINK),
                        // Reached the destination once feature A is on screen
                        WaitUntil.the(CartPage.ITEM_NAME, isVisible()).forNoMoreThan(15).seconds()
                ));
    }
}
