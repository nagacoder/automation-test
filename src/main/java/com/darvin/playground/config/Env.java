package com.darvin.playground.config;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

/** Reads environment-specific values from serenity.conf. */
public final class Env {
    private Env() {
    }

    public static String get(String key) {
        return EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty(key);
    }

    /** Like {@link #get(String)}, but fails fast with the missing key's name. */
    public static String require(String key) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing config value: " + key + " (check serenity.conf)");
        }
        return value;
    }

    /** Pause after each UI interaction, in ms (interaction.delay). */
    public static long interactionDelayMillis() {
        String value = get("interaction.delay");
        return value == null || value.isBlank() ? 0 : Long.parseLong(value.trim());
    }
}
