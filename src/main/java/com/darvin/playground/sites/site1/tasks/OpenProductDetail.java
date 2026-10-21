package com.darvin.playground.sites.site1.tasks;

import com.darvin.playground.interactions.Paced;
import com.darvin.playground.sites.site1.ui.InventoryPage;
import com.darvin.playground.sites.site1.ui.ProductDetailPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenProductDetail {
    public static Performable ofTheBackpack() {
        return Task.where("{0} opens the backpack detail page",
                Paced.steps(
                        Click.on(InventoryPage.BACKPACK_LINK),
                        // Reached the destination once feature A is on screen
                        WaitUntil.the(ProductDetailPage.PRODUCT_NAME, isVisible()).forNoMoreThan(15).seconds()
                ));
    }
}
