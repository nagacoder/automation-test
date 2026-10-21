package com.darvin.playground.sites.site3;

import com.darvin.playground.config.SiteConfig;
import com.darvin.playground.sites.Website;
import com.darvin.playground.sites.site3.tasks.CheckoutBackpack;
import com.darvin.playground.sites.site3.tasks.Login;
import com.darvin.playground.sites.site3.ui.CheckoutOverviewPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Website 3: feature A is the item name on the checkout overview, after filling a form. */
public class Site3 implements Website {

    private final SiteConfig config = SiteConfig.of("site3");

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
        return CheckoutBackpack.upToTheOverview();
    }

    @Override
    public Question<String> featureAText() {
        return Text.of(CheckoutOverviewPage.ITEM_NAME).describedAs("the checkout item name on " + name());
    }
}
