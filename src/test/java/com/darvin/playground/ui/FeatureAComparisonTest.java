package com.darvin.playground.ui;

import com.darvin.playground.base.BaseMultiSiteTest;
import com.darvin.playground.sites.Website;
import com.darvin.playground.sites.Websites;
import com.darvin.playground.sites.site1.Site1;
import com.darvin.playground.sites.site2.Site2;
import com.darvin.playground.sites.site3.Site3;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("ui")
@Tag("comparison")
@DisplayName("Feature A comparison")
class FeatureAComparisonTest extends BaseMultiSiteTest {

    @Test
    @DisplayName("Website 1 vs Website 2")
    void website1VsWebsite2() {
        compare(new Site1(), new Site2());
    }

    @Test
    @DisplayName("Website 1 vs Website 3")
    void website1VsWebsite3() {
        compare(new Site1(), new Site3());
    }

    @Test
    @DisplayName("Website 1 vs sites chosen in config (comparison.targets)")
    void website1VsConfiguredSites() {
        compare(Websites.main(), Websites.targets());
    }

    private void compare(Website main, Website... targets) {
        compare(main, List.of(targets));
    }

    /** Feature A text on every target must match the main site. */
    private void compare(Website main, List<Website> targets) {
        String expected = featureAOn(main);
        // A blank reference would make every site "match"
        assertThat(expected).as("Feature A on %s", main.name()).isNotBlank();

        Map<Website, String> actualBySite = new LinkedHashMap<>();
        for (Website target : targets) {
            actualBySite.put(target, featureAOn(target));
        }

        recordComparison(main, expected, actualBySite);

        // Soft assertions: report every mismatching site, not just the first one
        SoftAssertions softly = new SoftAssertions();
        actualBySite.forEach((site, actual) -> softly.assertThat(actual)
                .as("Feature A on %s compared to %s", site.name(), main.name())
                .isEqualTo(expected));
        softly.assertAll();
    }

    private String featureAOn(Website site) {
        Actor user = userOf(site);
        user.attemptsTo(site.logIn(), site.openFeatureA());
        return user.asksFor(site.featureAText()).trim();
    }

    /** Adds a side-by-side table to the Serenity report. */
    private void recordComparison(Website main, String expected, Map<Website, String> actualBySite) {
        StringBuilder table = new StringBuilder(main.name() + ": " + expected + "\n");
        actualBySite.forEach((site, actual) -> table
                .append(site.name()).append(": ").append(actual)
                .append(actual.equals(expected) ? "  -> MATCH" : "  -> MISMATCH")
                .append("\n"));
        Serenity.recordReportData().withTitle("Feature A comparison").andContents(table.toString());
    }
}
