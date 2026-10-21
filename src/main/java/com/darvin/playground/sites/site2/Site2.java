package com.darvin.playground.sites.site2;

import com.darvin.playground.config.SiteConfig;
import com.darvin.playground.sites.Website;
import com.darvin.playground.sites.site2.tasks.Login;
import com.darvin.playground.sites.site2.tasks.OpenCartWithBackpack;
import com.darvin.playground.sites.site2.ui.CartPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Website 2: feature A is the item name in the cart. */
public class Site2 implements Website {

    private final SiteConfig config = SiteConfig.of("site2");

    @Override
    public SiteConfig config() {
        return config;
    }

    @Override
    public Performable logIn() {
        return Login.with(config);
    }

    @Override
    public Performable openFeatureA() {
        return OpenCartWithBackpack.added();
    }

    @Override
    public Question<String> featureAText() {
        return Text.of(CartPage.ITEM_NAME).describedAs("the cart item name on " + name());
    }
}
