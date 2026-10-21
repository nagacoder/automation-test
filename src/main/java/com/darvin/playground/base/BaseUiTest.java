package com.darvin.playground.base;

import com.darvin.playground.tasks.NavigateTo;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/** Base for web tests: gives the actor a browser and opens the app. */
public abstract class BaseUiTest extends BaseTest {

    @Managed
    protected WebDriver driver;

    protected Actor sarah = Actor.named("Sarah");

    @BeforeEach
    void openApplication() {
        sarah.can(BrowseTheWeb.with(driver));
        sarah.attemptsTo(NavigateTo.theLoginPage());
    }
}
