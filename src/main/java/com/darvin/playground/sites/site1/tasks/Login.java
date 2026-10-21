package com.darvin.playground.sites.site1.tasks;

import com.darvin.playground.config.SiteConfig;
import com.darvin.playground.interactions.Paced;
import com.darvin.playground.sites.site1.ui.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;

public class Login {
    public static Performable with(SiteConfig config) {
        return Task.where("{0} logs in to " + config.name() + " as " + config.username(),
                Paced.steps(
                        Open.url(config.url()),
                        Enter.theValue(config.username()).into(LoginPage.USERNAME),
                        Enter.theValue(config.password()).into(LoginPage.PASSWORD),
                        Click.on(LoginPage.LOGIN_BUTTON)
                ));
    }
}
