package com.darvin.playground.sites.site1;

import com.darvin.playground.config.SiteConfig;
import com.darvin.playground.sites.Website;
import com.darvin.playground.sites.site1.tasks.Login;
import com.darvin.playground.sites.site1.tasks.OpenProductDetail;
import com.darvin.playground.sites.site1.ui.ProductDetailPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Website 1 (main): feature A is the product name on its detail page. */
public class Site1 implements Website {

    private final SiteConfig config = SiteConfig.of("site1");

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
        return OpenProductDetail.ofTheBackpack();
    }

    @Override
    public Question<String> featureAText() {
        return Text.of(ProductDetailPage.PRODUCT_NAME).describedAs("the product name on " + name());
    }
}
