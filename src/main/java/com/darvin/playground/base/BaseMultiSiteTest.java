package com.darvin.playground.base;

import com.darvin.playground.sites.Website;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Base for tests that work on several websites in one test.
 * Each site gets its own actor with its own browser, so sessions and cookies never mix.
 */
public abstract class BaseMultiSiteTest extends BaseTest {

    @BeforeEach
    void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @AfterEach
    void closeBrowsers() {
        OnStage.drawTheCurtain();
    }

    /** The actor (and browser) for a site; the same site always gets the same actor. */
    protected Actor userOf(Website site) {
        return OnStage.theActorCalled(site.name() + " user");
    }
}
