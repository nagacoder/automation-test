package com.darvin.playground.sites;

import com.darvin.playground.config.SiteConfig;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;

/**
 * What every website module provides for the feature A comparison.
 * Each module owns its locators and steps (sites/&lt;key&gt;/ui, sites/&lt;key&gt;/tasks), so sites can
 * differ in framework, flow and number of steps; only the connection details come from config.
 */
public interface Website {

    SiteConfig config();

    /** Logs in with this site's own credentials. */
    Performable logIn();

    /** All the steps from the landing page after login to the screen that shows feature A. */
    Performable openFeatureA();

    /** The feature A text, as shown on this site. */
    Question<String> featureAText();

    default String name() {
        return config().name();
    }
}
